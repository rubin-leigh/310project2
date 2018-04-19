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
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private String signUpStat;
	private String otherErr;
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
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
		String signUpStatus = jdbc.makeNewUser(userName, password);
		signUpStat = signUpStatus;
		System.out.println(signUpStatus);
		Gson gson = new Gson();
		Response res = new Response();
		if (userName.equals("") && password.equals("")) {
			res.setCompletedStatus("bothImcomplete");
			setOtherErr("bothImcomplete");
		} else if (userName.equals("")) {
			res.setCompletedStatus("incompleteUsername");
			setOtherErr("incompleteUsername");
		} else if (password.equals("")) {
			res.setCompletedStatus("incompletePassword");
			setOtherErr("incompletePassword");
		} else {
			res.setCompletedStatus("complete");
			setOtherErr("complete");
		}
		res.setSignUpStatus(signUpStatus);
		setSignUpStat(signUpStatus);
		if (signUpStatus.equals("successful") ) {		
			session.setAttribute("userName", userName);
		}
		jdbc.stop();
		String json = gson.toJson(res);
		PrintWriter out = response.getWriter();
		System.out.println(json);
		out.println(json);
		
		
		
	}

	

	public String getOtherErr() {
		return otherErr;
	}

	public void setOtherErr(String otherErr) {
		this.otherErr = otherErr;
	}

	public String getSignUpStat() {
		return signUpStat;
	}

	public void setSignUpStat(String signUpStat) {
		this.signUpStat = signUpStat;
	}

}