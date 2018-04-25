package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import LoginFiles.JDBC;
import LoginFiles.SignUpServlet;
public class SignUpTest {
	
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
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "complete");
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
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "complete");
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
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "complete");
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
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "bothImcomplete");
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
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "incompletePassword");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void TestSignUpEmptyUser() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("");
		Mockito.when(request.getParameter("password")).thenReturn("d");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "incompleteUsername");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void TestSignUpNotEmpty() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("d");
		Mockito.when(request.getParameter("password")).thenReturn("d");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getOtherErr(), "complete");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestJDBCCheckNewUserCorrect() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkNewUser("dan"), true);
		String oldUser = jdbc.makeNewUser("dan", "testPassword");
		assertEquals(oldUser, "UsernameExists");
		
		UUID uuid = UUID.randomUUID();
		String newUser = jdbc.makeNewUser("testUser" + uuid, "testPassword");
		assertEquals(newUser, "successful");
	}
	@Test
	public void TestJDBCCheckNewUserWrong() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkNewUser("none"), false);
		
	}
	@Test
	public void TestJDBCMakeNewUserCorrect() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkNewUser("newUser"), false);
	}
	@Test
	public void TestJDBCMakeNewUserWrong() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkNewUser("dan"), true);
	}
	
	
	
	
}
