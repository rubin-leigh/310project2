package server;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

public class CollageHandler {

	private String topic;
	private boolean borders; 
	private boolean rotations; 
	private String filter; 
	private String letters; 
	private int height;
	private int width;
	private Collage newCollage;
	private ImageTransform imageTransformer;

	//initializes the CollageHandler with String parameter t for collage topic
	public CollageHandler(String t, boolean borders, boolean rotations, String filter, String letters, int height, int width){
		topic = t;
		this.borders = borders; 
		this.rotations = rotations;
		this.filter = filter; 
		this.letters = letters;
		this.height = height;
		this.width = width;
	}

	//builds collage with ImageTransform object and returns Collage object
	public Collage build(){
		imageTransformer = new ImageTransform(topic, borders, rotations, filter, letters, 600, 800);
		BufferedImage completeCollage = imageTransformer.createCollageImage();
		Collage collageWrapper = new Collage();
		collageWrapper.setTopic(topic);
		collageWrapper.setImage(convertBufferedImageToBase64(completeCollage));
		return collageWrapper;
	}

	//converts BufferedImage paramater image into returned base64 string
	public String convertBufferedImageToBase64(BufferedImage image){

		String type = "png";
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			// writes bufferedImage to a string
			ImageIO.write(image, type, Base64.getEncoder().wrap(bos));
			imageString = bos.toString();

			bos.close();
		} catch (IOException e) {
			System.out.println("IOException while encoding collage image as a string");
		}
		return imageString;
	}

}
