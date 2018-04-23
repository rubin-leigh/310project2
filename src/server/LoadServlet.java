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

import LoginFiles.JDBC;

/**
 * Servlet implementation class LoadServlet
 */
@WebServlet("/LoadServlet")
public class LoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String status;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadServlet() {
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
		String currentUser = (String) session.getAttribute("userName");
		if (currentUser == null) {
			out.println("Error");
			this.setStatus("Error");
			return;
		} else {
			JDBC jdbc = new JDBC();
//			Collage currentCollage = (Collage) session.getAttribute("MainCollage");
//			ArrayList<Collage> previousCollages = (ArrayList<Collage>) session.getAttribute("PreviousCollageList");
			//previousCollages.add(0, currentCollage);
			ArrayList<Collage> collages = jdbc.getPreviousCollages(currentUser, false);
			Collage currentCollage = jdbc.getActiveCollage(currentUser, false);
			imageResponse ir = new imageResponse();
			Gson gson = new Gson();
			if (currentCollage == null) {
				ir.setEmpty(true);
				out.println(gson.toJson(ir));
				return;
			} else {
				session.setAttribute("MainCollage", currentCollage);
				session.setAttribute("PreviousCollageList", collages);
				
				ir.setImage(currentCollage);
				if (collages == null) {
					collages = new ArrayList<Collage>();
				}
				ir.setPreviousCollages(collages);
				ir.setEmpty(false);
				out.println(gson.toJson(ir));
				this.setStatus("Success");
				return;
			}
			//previousCollages.remove(0);
			
		}
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	



}
