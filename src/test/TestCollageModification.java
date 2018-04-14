package test;


import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;

import server.*;
import LoginFiles.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;



import static org.junit.Assert.*;

public class TestCollageModification {
	
	@Test
	public void TestLetterCreation() throws IOException {

		ImageTransform it = new ImageTransform("test", true, true, "None", "");
		String testString = "test";
		BufferedImage textImage = it.convertToTextImage(new BufferedImage(null, null, false, null));
		assertNotNull(textImage);
		
	}
}
