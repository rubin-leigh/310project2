package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import LoginFiles.JDBC;
import LoginFiles.LoginServlet;
import LoginFiles.Response;
public class LoginTest {
	
	@Test
	public void TestLoginSuccessful() throws IOException {
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("dan");
		Mockito.when(request.getParameter("password")).thenReturn("password");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getLoginStatus(), "successful");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void TestLoginBadUserName() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("wrong");
		Mockito.when(request.getParameter("password")).thenReturn("blah");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getLoginStatus(), "successful");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestLoginBadPassword() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("dan");
		Mockito.when(request.getParameter("password")).thenReturn("wrong");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getLoginStatus(), "wrongPassword");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void TestLoginEmptyAll() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "bothIncomplete");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void TestLoginEmptyPass() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("d");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "incompletePassword");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void TestLoginEmptyUser() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("");
		Mockito.when(request.getParameter("password")).thenReturn("d");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "incompleteUsername");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void TestLoginNotEmpty() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("d");
		Mockito.when(request.getParameter("password")).thenReturn("d");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		LoginServlet ls = new LoginServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "complete");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		assertEquals(jdbc.checkUserCredentials("wrong", "wrong"), "wrongPassword");
		
	}
	@Test
	public void TestResponseCompleted() {
		Response res = new Response();
		res.setCompletedStatus("3");
		assertEquals(res.getCompletedStatus(), "3");
	}
	@Test
	public void TestResponseLoginStatus() {
		Response res = new Response();
		res.setLoginStatus("3");
		assertEquals(res.getLoginStatus(), "3");
	}
	
}
