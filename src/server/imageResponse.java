package server;

import java.util.ArrayList;

public class imageResponse {
	private Collage image;
	private ArrayList<Collage> previousCollages;
	private boolean isEmpty;
	private String infoString;
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

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public String getInfoString() {
		return infoString;
	}

	public void setInfoString(String infoString) {
		this.infoString = infoString;
	}
	
}
