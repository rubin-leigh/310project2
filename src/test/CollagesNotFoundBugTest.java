package test;
 
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import LoginFiles.LoginServlet;
import server.*;

public class CollagesNotFoundBugTest {
	
	@Test
	public void TestTopicInMainController() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		RequestDispatcher rd = Mockito.mock(RequestDispatcher.class);
		Mockito.when(request.getParameter("topic")).thenReturn("dog");
		Mockito.when(request.getParameter("first")).thenReturn("true");
		Mockito.when(request.getParameter("borders")).thenReturn("true");
		Mockito.when(request.getParameter("rotations")).thenReturn("true");
		Mockito.when(request.getParameter("filter")).thenReturn("None");
		Mockito.when(request.getParameter("letters")).thenReturn("");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(request.getRequestDispatcher("/CollageViewerPage.jsp")).thenReturn(rd);
		
		MainController mc = new MainController();
		try {
			mc.doGet(request, response);
			assertEquals(mc.getTopic(), "dog");
			
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
