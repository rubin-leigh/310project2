package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import LoginFiles.JDBC;
import server.Collage;
import server.DeleteServlet;
import server.SaveServlet;

public class DeleteTests {
	@Test
	public void TestDeleteFromRequestGood() throws ServletException, IOException {
		Collage collage = new Collage();
		collage.setImage("test");
		collage.setTopic("test");
		ArrayList<Collage> collages = new ArrayList<Collage>();
		for (int i = 0; i < 5; i++) {
			Collage tempCollage = new Collage();
			tempCollage.setImage("test" + i);
			tempCollage.setTopic("test" + i);
			collages.add(tempCollage);
		}
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn("dan");
		Mockito.when(session.getAttribute("MainCollage")).thenReturn(collage);
		Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(collages);
		
		DeleteServlet ls = new DeleteServlet();
		ls.doGet(request, response);
		
		
		
	}
	@Test
	public void TestDeleteFromRequestBad() throws ServletException, IOException {
		Collage collage = new Collage();
		collage.setImage("test");
		collage.setTopic("test");
		ArrayList<Collage> collages = new ArrayList<Collage>();
		for (int i = 0; i < 5; i++) {
			Collage tempCollage = new Collage();
			tempCollage.setImage("test" + i);
			tempCollage.setTopic("test" + i);
			collages.add(tempCollage);
		}
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn("dan");
		Mockito.when(session.getAttribute("MainCollage")).thenReturn(collage);
		Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(null);
		
		DeleteServlet ls = new DeleteServlet();
		ls.doGet(request, response);
		
		
		
	}
	@Test
	public void TestDeleteFromRequestBad2() throws ServletException, IOException {
		Collage collage = new Collage();
		collage.setImage("test");
		collage.setTopic("test");
		ArrayList<Collage> collages = new ArrayList<Collage>();
		
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn("dan");
		Mockito.when(session.getAttribute("MainCollage")).thenReturn(collage);
		Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(collages);
		
		DeleteServlet ls = new DeleteServlet();
		ls.doGet(request, response);
		
		
		
	}
	@Test
	public void TestDeleteFromRequestBad3() throws ServletException, IOException {
		Collage collage = new Collage();
		collage.setImage("test");
		collage.setTopic("test");
		ArrayList<Collage> collages = new ArrayList<Collage>();
		
		collages.add(null);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn("dan");
		Mockito.when(session.getAttribute("MainCollage")).thenReturn(collage);
		Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(collages);
		
		DeleteServlet ls = new DeleteServlet();
		ls.doGet(request, response);
		
		
		
	}
	
}
