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
public class SignUpTest {
	
	@Test
	public void TestSignUpSuccessful() throws IOException {
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("john");
		Mockito.when(request.getParameter("password")).thenReturn("password");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getSignUpStat(), "successful");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void TestSignUpBadUserName() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("dan");
		Mockito.when(request.getParameter("password")).thenReturn("blah");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getSignUpStat(), "incompleteUsername");
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void TestSignUpBadPassword() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		PrintWriter pw = Mockito.mock(PrintWriter.class);
		Mockito.when(request.getParameter("username")).thenReturn("leigh");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(response.getWriter()).thenReturn(pw);
		SignUpServlet ls = new SignUpServlet();
		try {
			ls.service(request, response);
			assertEquals(ls.getSignUpStat(), "incompletePassword");
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
			assertEquals(ls.getOtherErr(), 3);
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
	public void TestJDBCCheckNewUserCorrect() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkNewUser("dan"), true);
		
	}
	@Test
	public void TestJDBCCheckNewUserWrong() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkNewUser("none"), false);
		
	}
	@Test
	public void TestJDBCMakeNewUserCorrect() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkNewUser("newUser"), "succesful");
	}
	@Test
	public void TestJDBCMakeNewUserWrong() {
		JDBC jdbc = new JDBC();
		assertEquals(jdbc.checkNewUser("dan"), "usernameExists");
	}
	
	
	
	
}
