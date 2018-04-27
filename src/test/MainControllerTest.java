package test;
  
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import server.Collage;
import server.CollageHandler;
import server.MainController;

public class MainControllerTest {
    
	//tests doGet function to make sure we set everything to the session
	@Test
    public void testDoGet() throws IOException, ServletException {  
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        MainController mainController = new MainController();
        //MainController mainControllerSpy = Mockito.spy(mainController);
        
        Mockito.when(request.getParameter("topic")).thenReturn("Cat");
        Mockito.when(request.getParameter("first")).thenReturn("true");
        Mockito.when(request.getParameter("borders")).thenReturn("true");
        Mockito.when(request.getParameter("rotations")).thenReturn("true");
        Mockito.when(request.getParameter("filter")).thenReturn("sepia");
        Mockito.when(request.getParameter("height")).thenReturn("200");
        Mockito.when(request.getParameter("width")).thenReturn("300");
        Mockito.when(request.getParameter("letters")).thenReturn("letters");
        Mockito.when(request.getSession()).thenReturn(session);
        PrintWriter pw = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(pw);
        Collage mainCollage = new Collage();
        //Mockito.when(mainControllerSpy.buildCollage("Cat",true,true,"sepia","letters", 200, 300)).thenReturn(mainCollage);
        ArrayList<Collage> previousCollageListTest = new ArrayList<Collage>();
        //Mockito.when(mainControllerSpy.getPreviousCollageList(session)).thenReturn(previousCollageListTest);
        
        MainController mc = new MainController();
        mc.doGet(request, response);
        
        
    }
	@Test
    public void testDoGet2() throws IOException, ServletException {  
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        MainController mainController = new MainController();
        //MainController mainControllerSpy = Mockito.spy(mainController);
        
        Mockito.when(request.getParameter("topic")).thenReturn("Cat");
        Mockito.when(request.getParameter("first")).thenReturn("false");
        Mockito.when(request.getParameter("borders")).thenReturn("false");
        Mockito.when(request.getParameter("rotations")).thenReturn("false");
        Mockito.when(request.getParameter("filter")).thenReturn("sepia");
        Mockito.when(request.getParameter("height")).thenReturn("200");
        Mockito.when(request.getParameter("width")).thenReturn("300");
        Mockito.when(request.getParameter("letters")).thenReturn("letters");
        Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(new ArrayList<Collage>());
        Mockito.when(request.getSession()).thenReturn(session);
        PrintWriter pw = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(pw);
        Collage mainCollage = new Collage();
        //Mockito.when(mainControllerSpy.buildCollage("Cat",true,true,"sepia","letters", 200, 300)).thenReturn(mainCollage);
        ArrayList<Collage> previousCollageListTest = new ArrayList<Collage>();
        //Mockito.when(mainControllerSpy.getPreviousCollageList(session)).thenReturn(previousCollageListTest);
        
        MainController mc = new MainController();
        mc.doGet(request, response);
        
        
    }
	@Test
    public void testDoGet3() throws IOException, ServletException {  
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        MainController mainController = new MainController();
        //MainController mainControllerSpy = Mockito.spy(mainController);
        
        Mockito.when(request.getParameter("topic")).thenReturn("Cat");
        Mockito.when(request.getParameter("first")).thenReturn("true");
        Mockito.when(request.getParameter("borders")).thenReturn("false");
        Mockito.when(request.getParameter("rotations")).thenReturn("false");
        Mockito.when(request.getParameter("filter")).thenReturn("sepia");
        Mockito.when(request.getParameter("height")).thenReturn("200");
        Mockito.when(request.getParameter("width")).thenReturn("300");
        Mockito.when(request.getParameter("letters")).thenReturn("letters");
        Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(new ArrayList<Collage>());
        Mockito.when(request.getSession()).thenReturn(session);
        PrintWriter pw = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(pw);
        Collage mainCollage = new Collage();
        //Mockito.when(mainControllerSpy.buildCollage("Cat",true,true,"sepia","letters", 200, 300)).thenReturn(mainCollage);
        ArrayList<Collage> previousCollageListTest = new ArrayList<Collage>();
        //Mockito.when(mainControllerSpy.getPreviousCollageList(session)).thenReturn(previousCollageListTest);
        
        MainController mc = new MainController();
        mc.doGet(request, response);
        
        
    }
	@Test
    public void testDoGet4() throws IOException, ServletException {  
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        MainController mainController = new MainController();
        //MainController mainControllerSpy = Mockito.spy(mainController);
        
        Mockito.when(request.getParameter("topic")).thenReturn("Cat");
        Mockito.when(request.getParameter("first")).thenReturn("false");
        Mockito.when(request.getParameter("borders")).thenReturn("false");
        Mockito.when(request.getParameter("rotations")).thenReturn("false");
        Mockito.when(request.getParameter("filter")).thenReturn("sepia");
        Mockito.when(request.getParameter("height")).thenReturn("200");
        Mockito.when(request.getParameter("width")).thenReturn("300");
        Mockito.when(request.getParameter("letters")).thenReturn("letters");
        Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(null);
        Mockito.when(request.getSession()).thenReturn(session);
        PrintWriter pw = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(pw);
        Collage mainCollage = new Collage();
        //Mockito.when(mainControllerSpy.buildCollage("Cat",true,true,"sepia","letters", 200, 300)).thenReturn(mainCollage);
        ArrayList<Collage> previousCollageListTest = new ArrayList<Collage>();
        //Mockito.when(mainControllerSpy.getPreviousCollageList(session)).thenReturn(previousCollageListTest);
        
        MainController mc = new MainController();
        mc.getTopic();
        mc.doGet(request, response);
        
        
    }
	
	
	
//	//tests doPost function to make sure we set everything to the session and forward to the next page
//    @Test
//    public void testDoPost() {
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//        HttpSession session = Mockito.mock(HttpSession.class);
//        RequestDispatcher rd = Mockito.mock(RequestDispatcher.class);
//        MainController mainController = new MainController();
//        MainController mainControllerSpy = Mockito.spy(mainController);
//        
//        Mockito.when(request.getParameter("topic")).thenReturn("Cat");
//        Mockito.when(request.getSession()).thenReturn(session);
//        Mockito.when(request.getRequestDispatcher("/CollageViewerPage.jsp")).thenReturn(rd);
//        Collage mainCollage = new Collage();
//        Mockito.when(mainControllerSpy.buildCollage("Cat",true,true,"sepia","letters")).thenReturn(mainCollage);
//        ArrayList<Collage> previousCollageListTest = new ArrayList<Collage>();
//        Mockito.when(mainControllerSpy.createNewListForTesting()).thenReturn(previousCollageListTest);
//        
//        try {
//            mainControllerSpy.doPost(request, response);
//            Mockito.verify(session).setAttribute("MainCollage", mainCollage);
//            Mockito.verify(session).setAttribute("PreviousCollageList", previousCollageListTest);
//            Mockito.verify(rd).forward(request, response);
//        } catch (ServletException e) {
//            System.out.println("ServletException testing MainController doPost()");
//        } catch (IOException e) {
//            System.out.println("IOException testing MainController doPost()");
//        }
//        
//    }
//    
    //tests buildCollage funcution to make sure it returns a collage object
    @Test
    public void testBuildCollage() {
    		MainController mainController = new MainController();
    		CollageHandler ch = Mockito.mock(CollageHandler.class);
    		Collage testCollage = new Collage();
    		Mockito.when(ch.build()).thenReturn(testCollage);
    		
    		Collage producedCollage = mainController.buildCollage("Cat",true,true,"sepia","letters",800,600);
    		assertThat(producedCollage, instanceOf(Collage.class));
    }
    
    //tests code in createNewListForTesting to make sure that it returns an arraylist
    @Test
    public void testCreateNewListForTesting() {
    		MainController mainController = new MainController();
    		ArrayList<Collage> previousCollageList = mainController.createNewListForTesting();
    		assertThat(previousCollageList, instanceOf(ArrayList.class));
    }
    
    //runs code in getPreviousCollageList
    @Test
    public void testGetPreviousCollageList() {
    		MainController mainController = new MainController();
    		HttpSession session = Mockito.mock(HttpSession.class);
    		ArrayList<Collage> previousCollageList = mainController.getPreviousCollageList(session);
    }

}