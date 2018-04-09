package DataContainers;


/**
 * Class defines a Picture object
 * @author leighrubin
 *
 */
public class Picture { 
	//dimensions of the Picture
	protected Pair<Integer, Integer> mDimensions;
	//source of the Picture
	protected String mSource;

	/**
	 * Constructs a Picture with a source, width, and height
	 * @param link The URL source of the picture
	 * @param width	The width of the picture
	 * @param height	 The height of the picture
	 */
	public Picture(String link, int width, int height) {
		mSource = link;
		mDimensions = new Pair<Integer, Integer>(width, height);
	}
	
	/**
	 * Set the dimensions (width, height) of the Picture
	 * @param dimensions A Pair representing the width and height
	 */
	public void setDimensions(Pair<Integer, Integer> dimensions) {
		this.mDimensions = dimensions;
	}
	
	/**
	 * Return the dimensions of the Picture
	 * @return A Pair representing the width and height
	 */
	public Pair<Integer, Integer> getDimensions() {
		return mDimensions;
	}
	
	/**
	 * Set the source of the Picture
	 * @param source The URL
	 */
	public void setSource (String source) {
		this.mSource = source;
	}
	
	/**
	 * Return the source of the Picture
	 * @return The URL
	 */
	public String getSource() {
		return mSource;
	}
	
}
