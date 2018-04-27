package server;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;


public class ImageTransform {
	private String topic;
	private List<BufferedImage> retrievedImages;
	private BufferedImage completeImage;
	
	private boolean borders; 
	private boolean rotations; 
	private String filter; 
	private String letters; 
	
	private int COLLAGE_WIDTH;
	private int COLLAGE_HEIGHT;
	private int COLLAGE_SIZE; // total number of pixels
	private int SCALED_IMAGE_SIZE;
	private int TOTAL_COMBINED_AREA;
	private int FINAL_FOREGROUND_IMAGE_SIZE;
//	private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyCQbxRMKMxuyaIVmosCa_k2sIv5BeavGFs";
//	private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyADYi8Ob0jmPJbGEMCkJwrB31bOY80RtXs";
//	private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyAh8tNso-_G-0h5DCft0JibbpPyLYhIPvE";
//	private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyB7_yytK04B7speZc4lXsHLr9ARmwPiUzw";
	private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyDp-v1bvX_Q1JUOCZx0G7cA4wkiEQ9ojFE"; //more requests
//	private static final String GOOGLE_CX = "007628912923159165220:9e6kozm2iea";	// custom search engine identifier
//	private static final String GOOGLE_CX = "008543189839369971484:b8selplq7z8";	// custom search engine identifier
//	private static final String GOOGLE_CX = "003847142776988134030:4xa5elxfuke";	// custom search engine identifier
//	private static final String GOOGLE_CX = "015527610641952349258:lx1x9pjo0ec";
	private static final String GOOGLE_CX = "017653595496843676433:6_pqqfbq6ia"; //more requests



	public ImageTransform(String t, boolean borders, boolean rotations, String filter, String letters, int height, int width) {
		this.topic = t;
		this.borders = borders; 
		this.rotations = rotations;
		this.filter = filter; 
		this.letters = letters;
		this.COLLAGE_HEIGHT = height;
		this.COLLAGE_WIDTH = width;
		System.out.println("collage width: " + COLLAGE_WIDTH);
		System.out.println("collage height: " + COLLAGE_HEIGHT);
		this.COLLAGE_SIZE = COLLAGE_WIDTH * COLLAGE_HEIGHT; // total number of pixels
		this.SCALED_IMAGE_SIZE = COLLAGE_SIZE/20;
		this.TOTAL_COMBINED_AREA = SCALED_IMAGE_SIZE*30;
		this.FINAL_FOREGROUND_IMAGE_SIZE = (TOTAL_COMBINED_AREA-COLLAGE_SIZE)/29;
		this.retrievedImages = new ArrayList<BufferedImage>();
	}
	
//	private Pixel[][] convertTo2D(BufferedImage image) {
//
//	      final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//	      final int width = image.getWidth();
//	      final int height = image.getHeight();
//	      final boolean hasAlphaChannel = image.getAlphaRaster() != null;
//
//	      int[][] result = new int[height][width];
//	      if (hasAlphaChannel) {
//	         final int pixelLength = 4;
//	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
//	            Pixel argb = new Pixel();
//	            argb.set (((int) pixels[pixel] & 0xff) << 24); // alpha
//	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
//	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
//	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
//	            result[row][col] = argb;
//	            col++;
//	            if (col == width) {
//	               col = 0;
//	               row++;
//	            }
//	         }
//	      } else {
//	         final int pixelLength = 3;
//	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
//	            int argb = 0;
//	            argb += -16777216; // 255 alpha
//	            argb += ((int) pixels[pixel] & 0xff); // blue
//	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
//	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
//	            result[row][col] = argb;
//	            col++;
//	            if (col == width) {
//	               col = 0;
//	               row++;
//	            }
//	         }
//	      }
//
//	      return result;
//	   }

	public BufferedImage createCollageImage() {
		if(this.fetchImages()) {
			this.resizeImages();
			this.combineImages();
			if (this.letters != "")
				return this.generateTextImage();
			else
				return this.completeImage;
		}

		return this.generateInsufficientNumberImage();
	}

	// retrieves top 30 image search results from Google Custom Search API
	// see: https://stackoverflow.com/questions/10257276/java-code-for-using-google-custom-search-api
	public boolean fetchImages() {
		
		try {
			// maintain count of number of results fetched from API
			int resultNum = 0;
			// initially fetch 40 images in case of bad/undownloadable links
			for(int i = 0; i < 5; i++) {
				URL requestURL = generateRequestURL(resultNum, "");

				HttpURLConnection connection = getConnectionFromRequestURL(requestURL);
				BufferedReader reader = generateBufferedReaderFromInputStream(connection);
				String output;

				// parse through JSON response for image links, line by line
				while((output = reader.readLine()) != null) {
					if(output.contains("\"link\": \"")) {
						String link = output.substring(output.indexOf("\"link\": \"") + ("\"link\": \"").length(), output.indexOf("\","));
						URL imageURL = new URL(link);
						try {
							BufferedImage resultImage = generateBufferedImageFromURL(imageURL);
							this.retrievedImages.add(resultImage);
						} catch(IIOException e) {
							System.out.println("Bad link/unable to generate buffered image from a url!");
						}
					}
				}
				if(this.retrievedImages.size() >= 30)
					break;
				
				connection.disconnect();
				resultNum += 10;
			}

		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return validateRetrievedImages();
	}
	
	// for testing
	public HttpURLConnection getConnectionFromRequestURL(URL requestURL) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
		connection.setRequestMethod("GET");
		return connection;
	}
	
	// for testing
	public BufferedReader generateBufferedReaderFromInputStream(HttpURLConnection connection) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		return reader;
	}
	
	// for testing
	public BufferedImage generateBufferedImageFromURL(URL imageURL) throws IOException {
		BufferedImage resultImage = (BufferedImage) ImageIO.read(imageURL);
		return resultImage;
	}

	public boolean validateRetrievedImages() {
		// verify there are no null images
		int numImages = this.retrievedImages.size();
		// remove any null images (links that blocked us)
		for(int i = 0; i < numImages; i++) {
			if(this.retrievedImages.get(i) == null) {
				this.retrievedImages.remove(i);
				i--;
				numImages--;
			}
		}

		// not enough images
		if(this.retrievedImages.size() < 30) {
			System.out.println("Was unable to find enough pictures! current size: " + this.retrievedImages.size());
			this.retrievedImages.clear();
			return false;
		}

		else {
			// remove any extra images from the end of the list
			while(this.retrievedImages.size() > 30) {
				this.retrievedImages.remove(this.retrievedImages.size()-1);
			}
			System.out.println("Retrieved necessary images and trimmed list, current size: " + this.retrievedImages.size());
			return true;
		}
	}

	// generate url to make request to our Google custom search engine
	public URL generateRequestURL(int resultNumber, String garbageString) throws MalformedURLException{
		URL requestURL = null;
		this.topic = this.topic.replaceAll(" ", "+");
		System.out.println(topic);
		if(resultNumber > 0) {
			requestURL = new URL("http" + garbageString + "s://www.googleapis.com/customsearch/v1?key=" + GOOGLE_SEARCH_API_KEY + "&cx=" + GOOGLE_CX + "&q=" + this.topic + "&searchType=image&imgType=photo&imgSize=medium&start=" + resultNumber + "&num=10");
		}
		else {
			requestURL = new URL("http" + garbageString + "s://www.googleapis.com/customsearch/v1?key=" + GOOGLE_SEARCH_API_KEY + "&cx=" + GOOGLE_CX + "&q=" + topic + "&searchType=image&imgType=photo&imgSize=medium&num=10");
		}
		return requestURL;
	}

	// scaling each image to roughly 1/20th of COLLAGE_SIZE, see: https://stackoverflow.com/questions/9417356/bufferedimage-resize
	public void resizeImages() {
		int numImages = this.retrievedImages.size();
		for(int i = 0; i < numImages; i++) {
			BufferedImage originalImage = this.retrievedImages.get(0);
			this.retrievedImages.remove(0);

			int originalWidth = originalImage.getWidth();
			int originalHeight = originalImage.getHeight();
			int originalImageSize = originalWidth * originalHeight;
			// determines scale factor such that the average sizes of the images is 1/20th of COLLAGE_SIZE
			double scaleFactor = originalImageSize/FINAL_FOREGROUND_IMAGE_SIZE;

			scaleFactor = Math.sqrt(scaleFactor);

			double scaledWidth = (originalWidth/scaleFactor);
			double scaledHeight = (originalHeight/scaleFactor);

			Image tmp = originalImage.getScaledInstance((int)50, (int)50, Image.SCALE_SMOOTH);
			BufferedImage resizedImage = new BufferedImage((int)50, (int)50, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2d = resizedImage.createGraphics();
			g2d.drawImage(tmp, 0, 0, null);

			this.retrievedImages.add(resizedImage);

			g2d.dispose();
		}
	}

	// generate random rotation amount for an image in radians within IMAGE_ROTATION_LIMIT
	// where -45 degrees <= IMAGE_ROTATION_LIMIT <= 45 degrees
	public int generateRotationAmount() {
		Random rand = new Random();
		int angle = rand.nextInt(46);

		if(rand.nextBoolean()) {
			return angle*-1;
		}

		return angle;
	}

	// adds 3px white frame around each image
	// see: https://stackoverflow.com/questions/4219511/draw-rectangle-border-thickness
	public void borderImage(BufferedImage image) {
			Graphics2D g2d = image.createGraphics();
			int height = image.getHeight();
			int width = image.getWidth();
			// sets border width to 3 pixels
			int borderWidth = 3;
			int borderControl = 1;
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(borderWidth));
			g2d.drawLine(0, 0, 0, height);
			g2d.drawLine(0, 0, width, 0);
			g2d.drawLine(0, height - borderControl, width, height - borderControl);
			g2d.drawLine(width - borderControl, height - borderControl, width - borderControl, 0);
			g2d.drawImage(image, 0, 0, null);
			g2d.dispose();
	}

	// generates collage from the retrieved bufferedImages
	public BufferedImage combineImages() {
		BufferedImage collageImage = new BufferedImage(COLLAGE_WIDTH, COLLAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) collageImage.getGraphics();
		int x = 0;
		int y = 0;
		int imageNum = 0;
		Random rand = new Random();
		for(int i = 0; i < 31; i++) {
			Collections.shuffle(this.retrievedImages);
			for(BufferedImage image : this.retrievedImages) {
//				if (imageNum == 0) {
//					Image tmp = image.getScaledInstance((int)COLLAGE_WIDTH, (int)COLLAGE_HEIGHT, Image.SCALE_SMOOTH);
//					BufferedImage resizedImage = new BufferedImage((int)COLLAGE_WIDTH, (int)COLLAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
//	
//					if(borders)
//					{
//						this.borderImage(resizedImage);
//					}
//	
//					Graphics2D g2d = resizedImage.createGraphics();
//					g2d.drawImage(tmp, 0, 0, null);
//					g.drawImage(resizedImage, 0, 0, null);
//				}
//				else {
					if(borders)
					{
						this.borderImage(image);
					}
					AffineTransform backup = g.getTransform();
					AffineTransform imageRotator = new AffineTransform();
					int rotationAmount = generateRotationAmount();
					
					if(rotations)
					{
						image = rotateImage(image,rotationAmount);
					}
					
					x = (int)((imageNum-0.25)*(COLLAGE_WIDTH/30));
					y = (int)((i-0.5)*(COLLAGE_HEIGHT/30));
					g.drawImage(image, null,x, y);
					g.setTransform(backup);
	//			}
	
				imageNum++;
				}	
			imageNum = 0;
			
		}
		
		this.completeImage = collageImage;
		applyFilter();
		
		try {
			ImageIO.write(this.completeImage,"png",new File(this.topic + "-collage.png"));
		} catch(IOException e) {
			e.getMessage();
		}
		return this.completeImage;
	}
	
	public BufferedImage generateTextImage()
	{
		BufferedImage originalImage = this.completeImage;
        final BufferedImage textImage = new BufferedImage(
            COLLAGE_WIDTH,
            COLLAGE_HEIGHT,
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = textImage.createGraphics();
        FontRenderContext frc = g.getFontRenderContext();
        int fontSize = Math.min((int)(COLLAGE_WIDTH*1.5)/letters.length(), Math.min(COLLAGE_HEIGHT,COLLAGE_HEIGHT*3/letters.length()));
        Font font = new Font(Font.MONOSPACED, Font.BOLD, fontSize);
        GlyphVector gv = font.createGlyphVector(frc, letters);
        Rectangle2D box = gv.getVisualBounds();
        int xOff = (COLLAGE_WIDTH-(int)(box.getWidth()))/2;
        int yOff = (COLLAGE_HEIGHT+(int)(box.getHeight()))/2;
        Shape shape = gv.getOutline(xOff,yOff);
        g.setClip(shape);
        g.drawImage(originalImage,0,0,null);
        g.setClip(null);
        g.setStroke(new BasicStroke(2f));
        g.setColor(Color.BLACK);
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.draw(shape);

        g.dispose();

        this.completeImage = textImage;
        return textImage;
	}
	
	public BufferedImage rotateImage(BufferedImage originalImage, double degree) {
		
		int width = originalImage.getWidth();
	    int height = originalImage.getHeight();
	    double toRad = Math.toRadians(degree);
	    int heightPrime = (int) (width * Math.abs(Math.sin(toRad)) + height * Math.abs(Math.cos(toRad)));
	    int widthPrime = (int) (height * Math.abs(Math.sin(toRad)) + width * Math.abs(Math.cos(toRad)));

	    BufferedImage rotatedImage = new BufferedImage(widthPrime, heightPrime, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = rotatedImage.createGraphics();
	    g.setComposite(AlphaComposite.SrcOver.derive(0.0f));
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 0, widthPrime, heightPrime);
	    g.translate(widthPrime/2, heightPrime/2);
	    g.rotate(toRad);
	    g.translate(-width/2, -height/2);
	    g.setComposite(AlphaComposite.SrcOver.derive(1f));
	    g.drawImage(originalImage, 0, 0, null);
	   
	    g.dispose();
	    return rotatedImage;
		
	}

	// generates the buffered image that is exported when there is an insufficient number of images found
	public BufferedImage generateInsufficientNumberImage() {
		BufferedImage insufficentNumberImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imageGraphicsManipulator = insufficentNumberImage.createGraphics();
				// setting font size to 18
        Font imageFont = new Font("Arial", Font.PLAIN, 18);
        imageGraphicsManipulator.setFont(imageFont);
        imageGraphicsManipulator.dispose();

				// setting presets for the Graphics2D object to be able to write the image
        insufficentNumberImage = new BufferedImage(COLLAGE_WIDTH, COLLAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        imageGraphicsManipulator = insufficentNumberImage.createGraphics();
        imageGraphicsManipulator.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        imageGraphicsManipulator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        imageGraphicsManipulator.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        imageGraphicsManipulator.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        imageGraphicsManipulator.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        imageGraphicsManipulator.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        imageGraphicsManipulator.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        imageGraphicsManipulator.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        imageGraphicsManipulator.setPaint(Color.WHITE);
        imageGraphicsManipulator.fillRect(0, 0, COLLAGE_WIDTH, COLLAGE_HEIGHT);
        imageGraphicsManipulator.setFont(imageFont);
        imageGraphicsManipulator.setColor(Color.BLACK);
        imageGraphicsManipulator.drawString("Insufficient number of images found", 445, COLLAGE_HEIGHT/2);
        imageGraphicsManipulator.dispose();

        return insufficentNumberImage;

    }

		// accessor method to return the retrieved images from Google/modified images at any point
	public List<BufferedImage> getRetrievedImages() {
		return this.retrievedImages;
	}
	
	public void setRetrievedImages(List<BufferedImage> images){
		this.retrievedImages = images;
	}

	// accessor method to return the complete collage image as a buffered image
	public BufferedImage getCompleteImage() {
		return this.completeImage;
	}
	
	public void applyFilter() {
		System.out.println(filter);
		if (filter.equals("sepia")) {
			applySepia();
		} else if (filter.equals("blackAndWhite")) {
			applyBlackAndWhite();
		} else if (filter.equals("grayscale")) {
			applyGrayscale();
		}
	}
	
	//https://www.dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-sepia-image
	public void applySepia() {
	    //get width and height of the image
	    int width = this.completeImage.getWidth();
	    int height = this.completeImage.getHeight();

	    //convert to sepia
	    for(int y = 0; y < height; y++){
	      for(int x = 0; x < width; x++){
	        int p = this.completeImage.getRGB(x,y);
	        
	        int a = (p>>24)&0xff;
	        int r = (p>>16)&0xff;
	        int g = (p>>8)&0xff;
	        int b = p&0xff;

	        //calculate tr, tg, tb
	        int tr = (int)(0.393*r + 0.769*g + 0.189*b);
	        int tg = (int)(0.349*r + 0.686*g + 0.168*b);
	        int tb = (int)(0.272*r + 0.534*g + 0.131*b);
	        

	        //check condition
	        if(tr > 255){
	          r = 255;
	        }else{
	          r = tr;
	        }

	        if(tg > 255){
	          g = 255;
	        }else{
	          g = tg;
	        }

	        if(tb > 255){
	          b = 255;
	        }else{
	          b = tb;
	        }

	        //set new RGB value
	        p = (a<<24) | (r<<16) | (g<<8) | b;
	        
	        this.completeImage.setRGB(x, y, p);
	      }
	    }
	  }
	
	public void applyGrayscale() {
		for (int x = 0; x < this.completeImage.getWidth(); ++x) {
		    for (int y = 0; y < this.completeImage.getHeight(); ++y)
		    {
		        int p = this.completeImage.getRGB(x, y);
		        
		        int a = (p>>24)&0xff;
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = p&0xff;
		        
		        int tr = (int)(0.299*r);
		        int tg = (int)(0.587*g);
		        int tb = (int)(0.114*b);
		        
		        int tt = tr+tg+tb;
		        
		        int gray = (a<<24) | (tt<<16) | (tt<<8) | tt;
		        
		        this.completeImage.setRGB(x, y, gray);
		    }
		}
	}
	
	public void applyBlackAndWhite() {
		int width = this.completeImage.getWidth();
	    int height = this.completeImage.getHeight();

	    for(int y = 0; y < height; y++){
	      for(int x = 0; x < width; x++){
	        int p = this.completeImage.getRGB(x,y);
	        
	        int a = (p>>24)&0xff;
	        int r = (p>>16)&0xff;
	        int g = (p>>8)&0xff;
	        int b = p&0xff;
	        
	        if (r+g+b > 255*3/2) {
	        		this.completeImage.setRGB(x, y, Color.WHITE.getRGB());
	        } else {
	        		this.completeImage.setRGB(x, y, Color.BLACK.getRGB());
	        }
	      }
	    }
	}

}
