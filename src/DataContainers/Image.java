package DataContainers;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class defines a single Image, which is an entity of a Collage, that
 * is defined by a position and a rotation angle.
 * @author leighrubin
 *
 */
public class Image extends Picture {
	//position Image will be place in Collage
	private Pair<Integer, Integer> mPosition;
	//rotation angle (degrees) of Image in Collage
	private int mRotation;
	private static final int MIN_ROTATION = -45;
	private static final int MAX_ROTATION = 46;
	
	/**
	 * Constructs an Image object with a source, width, and height.
	 * @param source The image source
	 * @param width The image width
	 * @param height The image height
	 */
	public Image(String source, int width, int height) {
		super(source, width, height);
		mPosition = new Pair<Integer, Integer>(0,0);
		this.setRandomRotation();
	}
	
	/**
	 * Return the position of the Image in the Collage
	 * @return The (x,y) coordinates
	 */
	public Pair<Integer, Integer> getPosition() {
		return mPosition;
	}
	
	/**
	 * Set the position of the Image in the Collage
	 * @param mPosition A Pair representing the position of the Image
	 */
	public void setPosition(Pair<Integer, Integer> mPosition) {
		this.mPosition = mPosition;
	}
	
	/**
	 * Return the rotation of the Image in the Collage
	 * @return The rotation angle in degrees
	 */
	public int getRotation() {
		return mRotation;
	}
	
	/**
	 * Set the rotation angle of the Image in the Collage
	 * @param mRotation The rotation angle in degrees
	 */
	public void setRotation(int mRotation) {
		this.mRotation = mRotation;
	}
	/**
	 * Set the rotation angle of the Image in the Collage to a random number
	 */
	public void setRandomRotation() {
		mRotation = ThreadLocalRandom.current().nextInt(MIN_ROTATION, MAX_ROTATION);
	}
}
