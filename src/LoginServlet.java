

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		//TODO make this secure
		HttpSession session = request.getSession();
		JDBC jdbc = new JDBC();
		int loginStatus = jdbc.checkUserCredentials(userName, password);
		System.out.println(loginStatus);
		Gson gson = new Gson();
		Response res = new Response();
		if (userName.equals("") && password.equals("")) {
			res.setCompletedStatus(1);
		} else if (userName.equals("")) {
			res.setCompletedStatus(2);
		} else if (password.equals("")) {
			res.setCompletedStatus(3);
		} else {
			res.setCompletedStatus(0);
		}
		res.setLoginStatus(loginStatus);
		if (loginStatus == 0) {		
			session.setAttribute("userName", userName);
		}
		jdbc.stop();
		String json = gson.toJson(res);
		PrintWriter out = response.getWriter();
		System.out.println(json);
		out.println(json);
		
		
		
	}

}
