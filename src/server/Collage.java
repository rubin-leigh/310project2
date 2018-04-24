package server;

import java.io.Serializable;

public class Collage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String topic;
	private String image;
	private String filter;
	private boolean border;
	private boolean rotation;
	public String aText ="";

	//reuturns collage topic
	public String getTopic(){
		return topic;
	}
	// returns collage image
	public String getImage(){
		return image;
	}

	//sets collage topic to String paramater t
	public void setTopic(String t){
		topic = t;
	}

	//sets collage image to String paramater i
	public void setImage(String i){
		image = i;
	}
	
	public void setBorder(boolean border) {
		this.border = border;
	}
	
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	public void setRotation(boolean rotation) {
		this.rotation = rotation;
	}
	
	public String getFilter() {
		return filter;
	}
	
	public boolean getBorder() {
		return border;
	}
	
	public boolean getRotation() {
		return rotation;
	}
	
}
