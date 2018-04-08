package DataContainers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import JUnit.*;
import javax.imageio.ImageIO;

public class Collage extends Picture {
	private ArrayList<Image> mImages;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int BORDER_SIZE = 3;
	private String mName;
	private static int id = 0;
	private ArrayList<String> extraImages;
	
	//used for testing purposes only
	public BufferedImage testImage;
	//used for testing purposes only
	public String testPath;
	
	//used for testing purposes only
	public ArrayList<Image> getImages() {
		return this.mImages;
	}
	/**
	 * Class defines a single Collage, with contains all of the images
	 */
	public Collage(String link, int width, int height) {
		super(link, width, height);
		mImages = new ArrayList<Image>();
	}
	
	/**
	 * Add an image to the collage
	 *  @param image The image to add
	 */
	public void addImage(Image image) {
		mImages.add(image);
	}
	
	/**
	 * Set the name of the collage
	 *  @param name The name of the collage
	 */
	public void setName(String name) {
		this.mName = name;
		//Uncomment this when running normally, comment out for testing purposes
		extraImages = Fetcher.extraImages(name); 
	}
	
	/**
	 * Set the name of the collage
	 *  @return the name of the collage
	 */
	public String getName(){
		return mName;
	}
	/**
	 * Download the images for the collage
	 *  @return the array of images
	 */
	public ArrayList<BufferedImage> downloadImages() {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		int index = 0;
		boolean retry = false;
		for (int i = 0; i < mImages.size(); i++) {
			
			BufferedImage image = null;
			
			try {
				URL url = null;
				if (retry) {
					url = new URL(extraImages.get(index));
					retry = false;
				} else {
					url = new URL(mImages.get(i).getSource());
				}
			    
			    HttpURLConnection myconn = (HttpURLConnection) url.openConnection();
			    myconn.addRequestProperty("User-Agent", "");
			    image = ImageIO.read(myconn.getInputStream());
			    //resizeImage(image, 10, 10);
			    if (image != null) {
			    	images.add(image);
			    } else {
			    	throw new IOException();
			    }
			    
			   
			} catch (Exception e) {
				index++;
				i--;
				retry = true;
				System.out.println("trying again");
			} 
		}
		return images;
	}
	/**
	 * Rotates the image to its given angle
	 * @param originalImage The image to be rotated
	 * @param degree The degree to which to rotate the image
	 * @return the rotated image
	 */
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
	/**
	 * Adds a white border to the image
	 * @param image The image to add the border to
	 * @return the image with the border
	 */
	public BufferedImage addBorder(BufferedImage image) {
		int width = image.getWidth() + 2*BORDER_SIZE;
		int height = image.getHeight() + 2*BORDER_SIZE;
		BufferedImage newImage = new BufferedImage(width, height, image.getType()); 
		Graphics2D g = newImage.createGraphics();  
		g.setColor(Color.WHITE);
	    g.fillRect(0, 0, width, height);
	    g.drawImage(image,  BORDER_SIZE,  BORDER_SIZE,  null);
	    return newImage;
	}
	/**
	 * Resizes the image to its given new height and width
	 * @param image The image to be resized
	 * @param newHeight the new height of the image
	 * @param newWidth the new width of the image
	 * @return the resized image
	 */
	public BufferedImage resizeImage(BufferedImage image, int newHeight, int newWidth) {
		 int width = image.getWidth();  
		 int height = image.getHeight();  
		 BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType()); 
		 Graphics2D g = newImage.createGraphics();  
		 g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		 g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, width, height, null);  
		 g.dispose();  
		 return newImage;
	}
	
	//converts the collage to a png, saves it to the server's HD, 
	//and returns the string with the location
	/**
	 * Converts the collage to a png and saves it to the server
	 * @param mPath The location on disk to save the image to
	 * @return the name of the image
	 */
	public String convertToPng(String mPath) {
		//find old images with same names and delete them
		ArrayList<BufferedImage> images = this.downloadImages();
		testImage = images.get(0);
		BufferedImage result = new BufferedImage(
                WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
		Graphics g = result.getGraphics();
		for (int i = 0; i < mImages.size(); i++) {
			
	        //BufferedImage bi = ImageIO.read(new File(image));
			Pair<Integer, Integer> dimensions = mImages.get(i).getDimensions();
			Pair<Integer, Integer> position = mImages.get(i).getPosition();
			double rotation = mImages.get(i).getRotation();
			BufferedImage currentImage = images.get(i);
			currentImage = this.resizeImage(currentImage, dimensions.getKey(), dimensions.getValue());
			currentImage = this.addBorder(currentImage);
			currentImage = this.rotateImage(currentImage, rotation);
	        g.drawImage(currentImage ,position.getKey(), position.getValue(), null);
	    }
		Collage.id++;
		JUnit.counter++;
		File file = new File(mPath + "Collage"+ id + ".png");
	    try {
	    	
			ImageIO.write(result, "png", file);
			
			System.out.println("File write complete! Saved to: "+ file.getAbsolutePath());
			testPath = file.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return "Collage"+ id + ".png";
	}
	
}
