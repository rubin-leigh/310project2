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

import LoginFiles.JDBC;

/**
 * Servlet implementation class SaveServlet
 */
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String status;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveServlet() {
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
			this.status = "Error";
			return;
		} else {
			JDBC jdbc = new JDBC();
			Collage currentCollage = (Collage) session.getAttribute("MainCollage");
			ArrayList<Collage> previousCollages = (ArrayList<Collage>) session.getAttribute("PreviousCollageList");
			previousCollages.add(0, currentCollage);
			jdbc.saveData(previousCollages, currentUser, false);
			previousCollages.remove(0);
			out.println("Success");
			this.status = "Success";
			return;
		}
		
	}

	public String getStatus() {
		return status;
	}



	

}
