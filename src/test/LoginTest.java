package test;
  
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import server.*;
import LoginFiles.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
public class LoginTest {
	
	@Test
	public void TestLoginSuccessful() throws IOException, ServletException{
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("dan");
		Mockito.when(request.getParameter("password")).thenReturn("password");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		ls.service(request, response);
		assertEquals(ls.getLoginStatus(), "successful");

		
		
		
	}
	
	@Test
	public void TestLoginBadUserName() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("wrong");
		Mockito.when(request.getParameter("password")).thenReturn("blah");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		ls.service(request, response);
		assertEquals(ls.getLoginStatus(), "wrongUsername");

	}
	@Test
	public void TestLoginBadPassword() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("dan");
		Mockito.when(request.getParameter("password")).thenReturn("wrong");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		ls.service(request, response);
		assertEquals(ls.getLoginStatus(), "wrongPassword");

	}
	@Test
	public void TestLoginEmptyAll() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		ls.service(request, response);
		assertEquals(ls.getOtherErr(), "bothIncomplete");
	}
	@Test
	public void TestLoginEmptyPass() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("d");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		ls.service(request, response);
		assertEquals(ls.getOtherErr(), "incompletePassword");
	}
	@Test
	public void TestLoginEmptyUser() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("");
		Mockito.when(request.getParameter("password")).thenReturn("d");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		ls.service(request, response);
		assertEquals(ls.getOtherErr(), "incompleteUsername");

	}
	@Test
	public void TestLoginNotEmpty() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("d");
		Mockito.when(request.getParameter("password")).thenReturn("d");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		ls.service(request, response);
		assertEquals(ls.getOtherErr(), "complete");
	}
	@Test
	public void TestJDBCCorrect() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkUserCredentials("dan", "password"), "successful");
		
	}
	@Test
	public void TestJDBCWrongPass() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkUserCredentials("dan", "wrong"), "wrongPassword");
		
	}
	@Test
	public void TestJDBCWrongAll() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkUserCredentials("wrong", "wrong"), "wrongUsername");
		
	}
	@Test
	public void TestResponseCompleted() {
		Response res = new Response();
		res.setCompletedStatus("successful");
		assertEquals(res.getCompletedStatus(), "successful");
	}
	@Test
	public void TestResponseLoginStatus() {
		Response res = new Response();
		res.setLoginStatus("successful");
		assertEquals(res.getLoginStatus(), "successful");
	}
	
}
