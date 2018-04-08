package DataContainers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class queries the GoogleImages API to get 30 images 
 * for the collage requested by the User.
 * @author leighrubin
 */
public class Fetcher {
	//API Key for GoogleImages search API
	private static String mApiKey="AIzaSyDp-v1bvX_Q1JUOCZx0G7cA4wkiEQ9ojFE";
	//Custom Search Engine unique identifier
	private static String mCseKey="cx=017653595496843676433:6_pqqfbq6ia";
	
	/**
	 * Function takes in a serach keywords and returns an ArrayList of
	 * 30 images from GoogleImages API.
	 * @param search The search terms
	 * @return List of Images
	 */
	public static ArrayList<Image> getImageList(String search) {
		ArrayList<Image> images = new ArrayList<Image>();
		/*
		 * GoogleImages free search API only returns 10 images per request. Google
		 * therefore recommends making multiple calls to the API, incrementing the starting
		 * index by 10 for each consecutive call. 
		 */
		for(int startIndex = 1; startIndex <= 30; startIndex += 10) {
			HttpURLConnection conn = null;
			try {
				String key=mApiKey;
				
				//Construct URL request
			    String qry = search.replace(' ', '+');
			    URL url = new URL(
			            "https://www.googleapis.com/customsearch/v1?key="+key+ "&" + mCseKey + "&q="+ qry + "&alt=json&searchType=image&imageSize=small&start=" + startIndex);
			    conn = (HttpURLConnection) url.openConnection();
			    conn.setRequestMethod("GET");
			    conn.setRequestProperty("Accept", "application/json");
			    
			    //Get data returned from request
			    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			    
			    //Initialize parsing variables
			    String output;
			    String link = "";
			    int width = 0;
			    int height = 0;
			    while ((output = br.readLine()) != null) {
			    		//Parse Json returned to extract the URL, width, and height of each image
			        if(output.contains("\"link\": \"")){   
			        		int linkStart = output.indexOf("\"link\": \"")+("\"link\": \"").length();
			        		int linkEnd =  output.indexOf("\",");
			            link=output.substring(linkStart, linkEnd);
			        }   
			        else if(output.contains("\"height\": ")){ 
			        		int heightStart = output.indexOf("\"height\": ")+("\"height\": ").length();
			        		int heightEnd = output.indexOf(",");
			            height=Integer.parseInt(output.substring(heightStart, heightEnd));
			        }  
			        else if(output.contains("\"width\": ")){
				        	int widthStart = output.indexOf("\"width\": ")+("\"width\": ").length();
			        		int widthEnd = output.indexOf(",");
			            width=Integer.parseInt(output.substring(widthStart, widthEnd));
			        } 
			        
			        //adds image to list if all properties are found in Json
			        if(link != "" && width != 0 && height != 0) {
				    		images.add(new Image(link,width,height));
				    		link = "";
				    		width = 0;
				    		height = 0;
				    }
			    }
			} catch (IOException ioe) {
				System.out.println("ioe: " + ioe.getMessage());
			} finally {
				//kill URL connection
			    if (conn != null) {
			    		conn.disconnect();
			    }
			}
		}
		return images;
	}
	
	/**
	 * Returns 10 extra images in the case that an image fails to
	 * download when constructing the Collage
	 * @param search The search query
	 * @return The images URLs
	 */
	public static ArrayList<String> extraImages(String search) {	
		ArrayList<String> extras = new ArrayList<String>();
		HttpURLConnection conn = null;
		try {
			String key=mApiKey;
			
			//Construct URL request
		    String qry = search.replace(' ', '+');
		    URL url = new URL(
		            "https://www.googleapis.com/customsearch/v1?key="+key+ "&" + mCseKey + "&q="+ qry + "&alt=json&searchType=image&start=31");
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Accept", "application/json");
		    
		    //Get data returned from request
		    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		    
		    //Initialize parsing variables
		    String output;
		    String link = "";
		    while ((output = br.readLine()) != null) {
		    		//Parse Json returned to extract the URL of the image
		        if(output.contains("\"link\": \"")){   
		        		int linkStart = output.indexOf("\"link\": \"")+("\"link\": \"").length();
		        		int linkEnd =  output.indexOf("\",");
		            link=output.substring(linkStart, linkEnd);
		            extras.add(link);
		        }
		    }
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		} finally {
			//kill URL connection
		    if (conn != null) {
		    		conn.disconnect();
		    }
		}
		//return nothing if no link is found
		return extras;
	}
}
