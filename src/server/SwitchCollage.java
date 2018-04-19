package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class SwitchCollage
 */
@WebServlet("/SwitchCollage")
public class SwitchCollage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchCollage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String stringId = request.getParameter("id");
		int id = Integer.parseInt(stringId);
		HttpSession session = request.getSession();
		ArrayList<Collage> collages = (ArrayList<Collage>) session.getAttribute("PreviousCollageList");
		Collage currentCollage = (Collage) session.getAttribute("MainCollage");
		
		Collage clickedCollage = collages.get(id);
		session.setAttribute("MainCollage", clickedCollage);
		collages.remove(id);
		collages.add(0, currentCollage);
		
		imageResponse ir = new imageResponse();
		ir.setImage(clickedCollage);
		ir.setPreviousCollages(collages);
		
		session.setAttribute("previousCollageList", collages);
		
		Gson gson = new Gson();
		String returnString = gson.toJson(ir);
		out.println(returnString);
	}

	
}
