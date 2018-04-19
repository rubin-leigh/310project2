package server;

import java.util.ArrayList;

public class imageResponse {
	private Collage image;
	private ArrayList<Collage> previousCollages;
	public Collage getImage() {
		return image;
	}

	public void setImage(Collage image) {
		this.image = image;
	}

	public ArrayList<Collage> getPreviousCollages() {
		return previousCollages;
	}

	public void setPreviousCollages(ArrayList<Collage> previousCollages) {
		this.previousCollages = previousCollages;
	}
	
}
