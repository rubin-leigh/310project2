package DataContainers;

import java.util.ArrayList;

/**
 * Initializes Images to be constructed into a Collage.
 * @author leighrubin
 *
 */
public class CollageGenerator {
	//width and height of the collage
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	//width and height for indiviual images in the collage 
	private static final int SMALL_WIDTH = 179;
	private static final int SMALL_HEIGHT = 134;
	private ArrayList<Image> images;
	private Collage mCollage;
	
	/**
	 * Initialize the Collage with a WIDTH and a HEIGHT.
	 */
	public CollageGenerator() {
		this.mCollage = new Collage("",WIDTH, HEIGHT);
		this.images = new ArrayList<Image>();
	}
	public ArrayList<Image> getImages() {
		return this.images;
	}
	/**
	 * Retrieves and manipulates images to be placed into the collage by
	 * the Collage class. This function adjust the size, rotation angle,
	 * and position of each individual image.
	 * @param query Search query
	 * @param mPath Path to collage
	 * @return Path to collage of 30 images
	 * @return "ERROR" if insufficeint images are found
	 */
	public String buildCollage(String query, String mPath, ArrayList<Image> images) {
		//set the name of the Collage to what was searched
		this.mCollage.setName(query);
		
		//retrieve images from Google API
		if (images != null) {
			this.images = images;
		} else {
			this.images = Fetcher.getImageList(query);
		}
		
		
		//check if insufficient images found
		if(this.images.size() < 30) {
			return "ERROR";
		}
		int height = SMALL_HEIGHT;
		int width = SMALL_WIDTH;
		
		//keeps track on which image it's currently on
		int counter = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				Image currentImage = this.images.get(counter);
				//give it its dimensions
				currentImage.setDimensions(new Pair<Integer, Integer>(width,height));
				//give it an appropriate position
				currentImage.setPosition(new Pair<Integer, Integer>((int) ((double)WIDTH*((double)((double)(i-.2))/5)),(int) ((double)HEIGHT*(double)(((double)(j-.2))/(double)6)))); 
				//add it to collage
				mCollage.addImage(currentImage);
				counter++;
			}
			
		}
		//return the path to where the collage should be constructed
		return mCollage.convertToPng(mPath);
	}
	public Collage getCollage() {
		return this.mCollage;
	}
	
}
