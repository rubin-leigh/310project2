package JUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.mockito.*;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.*;
import DataContainers.*;

public class JUnit {
	 CollageGenerator cg = new CollageGenerator();
	 public static int counter = 0;
	 Collage collage;
	 ArrayList<Image> images = Fetcher.getImageList("puppy");
	 ArrayList<String> extraImages = Fetcher.extraImages("puppy");
	    
	 
	 @Mock
	 HttpServletRequest req;
	 @Mock
	 HttpServletResponse res;
	 @Mock
	 HttpSession session;


	

	 @Before
	 public void setUp() throws Exception {
	  MockitoAnnotations.initMocks(this);
	  
	 }

	 
	 @Test
	   public void testFetcherGetImageList() {    
	      assertEquals(images.size(),30);
	   }
	   
	   @Test
	   public void testFetcherExtraImages() {
	      assertEquals(extraImages.size(),10);
	   }
	   
	   @Test
	   public void testPictureConstructor() {
	       Picture testPic = new Picture("testSrc", 100, 100);
	       assertTrue(testPic.getSource().equals("testSrc"));
	       assertEquals(testPic.getDimensions().getKey().intValue(),100);
	       assertEquals(testPic.getDimensions().getValue().intValue(),100);
	   }
	   
	   @Test(expected = NullPointerException.class)
	   public void testFetcherInvalidUrlGetImageList() {
	       String longString = "";
	       for(int i = 0; i < 10000; i++) {
	           longString = longString.concat("a");
	       }
	       ArrayList<Image> none = Fetcher.getImageList(longString);
	       assertEquals(none.size(),0);
	       
	       Fetcher.getImageList(null);
	   }
	   
	   @Test(expected = NullPointerException.class)
	   public void testFetcherInvalidUrlExtraImages() {
	       String longString = "";
	       for(int i = 0; i < 10000; i++) {
	           longString = longString.concat("a");
	       }
	       ArrayList<String> none = Fetcher.extraImages(longString);
	       assertEquals(none.size(),0);
	       
	       Fetcher.extraImages(null);
	   }
	   
	   @Test
	   public void testPictureSetSource() {
	       Picture testPic = new Picture("testSrc", 100, 100);
	       testPic.setSource("newSrc");
	       assertTrue(testPic.getSource().equals("newSrc"));
	   }
	   
	   @Test
	   public void testPictureGetSource() {
	       Picture testPic = new Picture("testSrc", 100, 100);
	       assertTrue(testPic.getSource().equals("testSrc"));
	   }
	   
	   @Test
	   public void testPictureSetDimensions() {
	       Picture testPic = new Picture("testSrc", 100, 100);
	       testPic.setDimensions(new Pair<Integer,Integer>(200,400));
	       assertEquals(testPic.getDimensions().getKey().intValue(),200);
	       assertEquals(testPic.getDimensions().getValue().intValue(),400);
	   }
	   
	   @Test
	   public void testPictureGetDimensions() {
	       Picture testPic = new Picture("testSrc", 100, 100);
	       assertEquals(testPic.getDimensions().getKey().intValue(),100);
	       assertEquals(testPic.getDimensions().getValue().intValue(),100);
	   }
	   
	   @Test
	   public void testRandomRotation() {
	       for(Image i: images) {
	           assertTrue(i.getRotation() <= 45);
	           assertTrue(i.getRotation() >= -45);
	       }
	       
	       Image testImage = new Image("testSrc",100,100);
	       int low = Integer.MAX_VALUE;
	       int high = Integer.MIN_VALUE;
	       for(int i = 0; i < 1000000; i++) {
	           testImage.setRandomRotation();
	           low = Integer.min(testImage.getRotation(),low);
	           high = Integer.max(testImage.getRotation(), high);
	           
	           assertTrue(testImage.getRotation() <= 45);
	           assertTrue(testImage.getRotation() >= -45);
	       }
	       
	       assertEquals(low, -45);
	       assertEquals(high, 45);
	   }
	   
	   @Test
	   public void testImageConstructor() {
	       Image testImage = new Image("testSrc",100,100);
	       assertEquals(testImage.getSource(),"testSrc");
	       assertEquals(testImage.getDimensions().getKey().intValue(),100);
	       assertEquals(testImage.getDimensions().getValue().intValue(),100);
	       assertEquals(testImage.getPosition().getKey().intValue(),0);
	       assertEquals(testImage.getPosition().getValue().intValue(),0);
	   }
	   
	   @Test
	   public void testImageSetPosition() {
	       Image testImage = new Image("testSrc",100,100);
	       testImage.setPosition(new Pair<Integer, Integer>(100,205));
	       
	       assertEquals(testImage.getPosition().getKey().intValue(),100);
	       assertEquals(testImage.getPosition().getValue().intValue(),205);
	   }
	   
	   @Test
	   public void testImageGetPosition() {
	       Image testImage = new Image("testSrc",100,100);
	       
	       assertEquals(testImage.getPosition().getKey().intValue(),0);
	       assertEquals(testImage.getPosition().getValue().intValue(),0);
	   }
	   
	   @Test
	   public void testImageSetRotation() {
	       Image testImage = new Image("testSrc",100,100);
	       
	       testImage.setRotation(20);
	       assertEquals(testImage.getRotation(),20);
	   }
	   
	   @Test
	   public void testImageGetRotation() {
	       Image testImage = new Image("testSrc",100,100);
	       
	       testImage.setRotation(30);
	       assertEquals(testImage.getRotation(),30);
	   }
	   
	   @Test
	   public void testCollageGeneratorConstructor() {
	       CollageGenerator cg = new CollageGenerator();
	       assertEquals(cg.getImages().size(),0);
	   }
	   
	   @Test
	   public void testCollageGeneratorBuildCollage() {
	       CollageGenerator cg = new CollageGenerator();
	       String path = cg.buildCollage("USC Trojans", "testPath", null);
	       
	       int totalArea = 0;
	       for(Image i: cg.getImages()) {
	           totalArea += (i.getDimensions().getKey().intValue()) * (i.getDimensions().getValue().intValue());
	       }
	       
	       int availableArea = (800*600*30)/20;
	       assertTrue(totalArea >= availableArea*.99);
	       assertTrue(totalArea <= availableArea*1.01);
	       assertTrue(!(path.equals("ERROR")));
	   }
	   
	   @Test
	   public void testCollageGeneratorBuildCollageWithImages() {
	       CollageGenerator cg = new CollageGenerator();
	       cg.buildCollage("puppy", "testPath", images);
	   }
	   
	   @Test
	   public void testCollageGeneratorBuildCollageInsufficientImagesFound() {
	       CollageGenerator cg = new CollageGenerator();
	       String path = cg.buildCollage("aosibgoiwebgoernwae", "testPath", null);
	       
	       assertTrue(path.equals("ERROR"));
	       assertTrue(cg.getImages().size() < 30);
	   }
	   
	   @Test
	   public void testMainServletService() throws Exception {
	       HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
	       HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
	       HttpSession session = Mockito.mock(HttpSession.class);
	       
	       Mockito.when(req.getParameter("query")).thenReturn("puppy");
	       StringWriter stringWriter = new StringWriter();
	       PrintWriter writer = new PrintWriter(stringWriter);
	       Mockito.when(resp.getWriter()).thenReturn(writer);
	       Mockito.when(req.getSession()).thenReturn(session);
	       new MainServlet().service(req, resp);
	       Mockito.verify(req, Mockito.atLeast(1)).getParameter("query"); // only if you want to verify username was called...
	       writer.flush(); // it may not have been flushed yet...
	       assertTrue(stringWriter.toString().contains("{\"src\": \""));
	       assertTrue(stringWriter.toString().contains("\"}"));
	   }
	   
	   @Test
	   public void testMainServletServiceInsufficientImages() throws Exception {
	       HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
	       HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
	       HttpSession session = Mockito.mock(HttpSession.class);
	       Mockito.when(req.getParameter("query")).thenReturn("fasdfasdfasdgas");
	       StringWriter stringWriter = new StringWriter();
	       PrintWriter writer = new PrintWriter(stringWriter);
	       Mockito.when(resp.getWriter()).thenReturn(writer);
	       Mockito.when(req.getSession()).thenReturn(session);
	       new MainServlet().service(req, resp);
	       Mockito.verify(req, Mockito.atLeast(1)).getParameter("query");
	       writer.flush();
	       assertTrue(stringWriter.toString().contains("{\"src\": \"images/NotEnoughImages.png\"}"));
	   }
	 
	 
	 
	 @Test
	 public void checkRectangular() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 assertEquals( "Dimension of collage must be at least 800", 800, (int)collage.getDimensions().first);
		 assertEquals( "Dimension of collage must be at least 600", 600, (int)collage.getDimensions().second);
	 }
	 @Test
	 public void checkAllImagesExist() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 ArrayList<Image> images = collage.getImages();
		 assertEquals("There must be 30 images in collage", images.size(), 30);
	 }
	 @Test
	 public void checkBordersAdded() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 BufferedImage testImage = collage.testImage;
		 BufferedImage newImage = collage.addBorder(testImage);
		 assertEquals("Adds 3px  border to image on top and bottom", testImage.getHeight() + 6, newImage.getHeight());
		 assertEquals("Adds 3px border to image on left and right", testImage.getWidth() + 6, newImage.getWidth());
	 }
	 @Test 
	 public void checkImageResize() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 BufferedImage testImage = collage.testImage;
		 BufferedImage newImage = collage.resizeImage(testImage, 10, 15);
		 assertEquals("Resizes image to defined height ", newImage.getHeight(), 10);
		 assertEquals("Resizes image to defined width ", newImage.getWidth(), 15);
	 }
	 @Test
	 public void checkNameSetToSearchTerm() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 assertEquals("Check if name is set to search term", "puppies", collage.getName());
	 }
	 @Test
	 public void checkNameDefine() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 collage.setName("test");
		 assertEquals("Check if name is set to defined name", "test", collage.getName());
	 }
	 @Test
	 public void checkDownload() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 ArrayList<BufferedImage> testImages = collage.downloadImages();
		 assertEquals("Check if 30 images are downloaded using download function", testImages.size(), 30);
	 }
	 @Test 
	 public void checkDownloadNotNull() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 ArrayList<BufferedImage> testImages = collage.downloadImages();
		 testImages.parallelStream().forEach(im -> assertNotNull("check if image is not null using download function", im));
	 }
	 @Test 
	 public void checkRotateResize() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 BufferedImage testImage = collage.testImage;
		 BufferedImage newImage = collage.rotateImage(testImage, 45);
		 assertEquals("Resizes rotated image to appropriate height to fix the rotated image", newImage.getHeight(), 1414);
		 assertEquals("Resizes rotated image to appropriate width to fit the rotated image", newImage.getWidth(), 1414);
	 }
	 @Test
	 public void checkCollageName() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 String name = collage.convertToPng("test");
		 assertEquals("Check to see if the collage uses the correct name", "Collage"+ counter + ".png", name);
		 
	 }
	 @Test
	 public void checkCollagePath() {
		 cg.buildCollage("puppies", "", null);
		 collage = cg.getCollage();

		 collage.convertToPng("test");
		 Path currentRelativePath = Paths.get("");
		 String path = currentRelativePath.toAbsolutePath().toString();
		 assertEquals("Check to see if convertToPng saves to correct path", path+"/testCollage" + counter + ".png", collage.testPath);

	 }

}
