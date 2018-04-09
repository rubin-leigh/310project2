package LoginFiles;


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
	private String loginStat;
	private String otherErr;
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
		String loginStatus = jdbc.checkUserCredentials(userName, password);
		System.out.println(loginStatus);
		Gson gson = new Gson();
		Response res = new Response();
		if (userName.equals("") && password.equals("")) {
			res.setCompletedStatus("bothIncomplete");
			setOtherErr("bothIncomplete");
		} else if (userName.equals("")) {
			res.setCompletedStatus("incompleteUsername");
			setOtherErr("incompleteUsername");
		} else if (password.equals("")) {
			res.setCompletedStatus("incompletePassword");
			setOtherErr("incompletePassword");
		} else {
			res.setCompletedStatus("complete");
			setOtherErr("complete");
		}.
		res.setLoginStatus(loginStatus);
		loginStat = loginStatus;
		if (loginStatus.equals("successful")) {		
			session.setAttribute("userName", userName);
		}
		jdbc.stop();
		String json = gson.toJson(res);
		PrintWriter out = response.getWriter();
		System.out.println(json);
		out.println(json);
		
		
		
	}

	public String getLoginStatus() {
		return loginStat;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStat = loginStatus;
	}

	public String getOtherErr() {
		return otherErr;
	}

	public void setOtherErr(String otherErr) {
		this.otherErr = otherErr;
	}

}
