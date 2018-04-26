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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		ArrayList<Collage> collages = (ArrayList<Collage>) session.getAttribute("PreviousCollageList");
		Collage currentCollage = (Collage) session.getAttribute("MainCollage");
		imageResponse ir = new imageResponse();
		if (collages != null && collages.size() > 0 && collages.get(0) != null) {
			session.setAttribute("MainCollage", collages.get(0));
			ir.setImage(collages.get(0));
			collages.remove(0);
			session.setAttribute("previousCollageList", collages);
		} else {
			session.setAttribute("MainCollage", null);
			ir.setImage(null);
			ir.setEmpty(true);
		}

		
		ir.setPreviousCollages(collages);
		
		
		Gson gson = new Gson();
		String returnString = gson.toJson(ir);
		out.println(returnString);
	}


}
