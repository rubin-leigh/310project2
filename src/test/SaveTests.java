package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import LoginFiles.JDBC;
import LoginFiles.SignUpServlet;
import server.Collage;
import server.LoadServlet;
import server.SaveServlet;

public class SaveTests {
	@Test
	public void TestSaveFromRequestGood() throws ServletException, IOException {
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
		
		SaveServlet ls = new SaveServlet();
		ls.doGet(request, response);
		assertEquals(ls.getStatus(), "Success");
		JDBC jdbc = new JDBC();
		jdbc.resetDatabase(false);
		
		
		
	}
	@Test
	public void TestSaveFromRequestBad() throws IOException, ServletException {
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
		Mockito.when(session.getAttribute("userName")).thenReturn(null);
		Mockito.when(session.getAttribute("MainCollage")).thenReturn(collage);
		Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(collages);
		
		SaveServlet ls = new SaveServlet();

		ls.doGet(request, response);
		assertEquals(ls.getStatus(), "Error");
		JDBC jdbc = new JDBC();
		jdbc.resetDatabase(false);
		
		
	}
	@Test
	public void TestSaveFromRequestWrongUser() throws ServletException, IOException {
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
		Mockito.when(session.getAttribute("userName")).thenReturn("dann");
		Mockito.when(session.getAttribute("MainCollage")).thenReturn(collage);
		Mockito.when(session.getAttribute("PreviousCollageList")).thenReturn(collages);
		
		SaveServlet ls = new SaveServlet();
		ls.doGet(request, response);
		assertEquals(ls.getStatus(), "Success");
		JDBC jdbc = new JDBC();
		jdbc.resetDatabase(false);
		
		
		
	}
	@Test
	public void TestSaveThrowException() throws ServletException, IOException {
		
		ArrayList<Collage> collages = new ArrayList<Collage>();
		for (int i = 0; i < 5; i++) {
			Collage tempCollage = new Collage();
			tempCollage.setImage("test" + i);
			tempCollage.setTopic("test" + i);
			collages.add(tempCollage);
		}
		
		JDBC jdbc = new JDBC();
		
		jdbc.saveData(collages, "dan", true);

		jdbc.resetDatabase(false);
	}
	
	@Test 
	public void LoadTestCorrect() throws ServletException, IOException{
		
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn("dan");
		LoadServlet ls = new LoadServlet();
		ls.doGet(request, response);
		assertEquals(ls.getStatus(), "Success");
		
	}
	@Test 
	public void LoadTestWrong() throws ServletException, IOException{
		
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn(null);
		LoadServlet ls = new LoadServlet();
		ls.doGet(request, response);
		assertEquals(ls.getStatus(), "Error");
		
	}
	@Test 
	public void LoadTestEmpty() throws ServletException, IOException{
		
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn("bob");
		LoadServlet ls = new LoadServlet();
		ls.doGet(request, response);
		
	}
	@Test 
	public void LoadTestWrongUser() throws ServletException, IOException{
		
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn("bla");
		LoadServlet ls = new LoadServlet();
		ls.doGet(request, response);
		
	}
	
	@Test
	public void TestExceptionLoad() {
		JDBC jdbc = new JDBC();
		jdbc.getActiveCollage("dan", true);
		jdbc.getPreviousCollages("dan", true);
		
	}
	@Test
	public void TestExceptionResetDatabse() {
		JDBC jdbc = new JDBC();
		jdbc.resetDatabase(true);
		
	}
	@Test 
	public void TestNoPreviousCollages() throws IOException, ServletException{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(session.getAttribute("userName")).thenReturn("test");
		LoadServlet ls = new LoadServlet();
		ls.doGet(request, response);
		assertEquals(ls.getStatus(), "Success");
	}
}
