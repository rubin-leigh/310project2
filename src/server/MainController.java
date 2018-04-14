package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String topic;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

    //Handles setting the initial MainCollage & PreviousCollageList
    //Also creates
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException  {
				String topic = request.getParameter("topic");
				this.setTopic(topic);
				System.out.println(topic);
				String first = request.getParameter("first");
				String stringBorders = request.getParameter("borders");
				boolean borders;
				if(stringBorders.equals("true")) {
					borders = true;
				} else {
					borders = false;
				}
				
				String stringRotation = request.getParameter("rotations");
				boolean rotations;
				if (stringRotation.equals("true")) {
					rotations = true;
				} else {
					rotations = false;
				}
				String filter = request.getParameter("filter");
				String letters = request.getParameter("letters");
				Collage topicCollage = buildCollage(topic, borders, rotations, filter, letters);
				HttpSession session = request.getSession();
				ArrayList<Collage> previousList;
				if(first.equals("true")) {
					previousList = new ArrayList<Collage>();
					session.setAttribute("PreviousCollageList", previousList);
				} else {
					previousList = getPreviousCollageList(session);
					
				}
				
				previousList.add((Collage) session.getAttribute("MainCollage"));
				session.setAttribute("MainCollage", topicCollage);
				imageResponse ir = new imageResponse();
				ir.setImage(topicCollage);
				Gson gson = new Gson();
				String imageResponse = gson.toJson(ir);
				PrintWriter out = response.getWriter();
				out.println(imageResponse);
				//request.getRequestDispatcher("/CollageViewerPage.jsp").forward(request, response);
				return;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException  {
				String topic = request.getParameter("topic");
				boolean borders = true;
				boolean rotations = true;
				String filter = "None";
				String letters = "Test";
				Collage topicCollage = buildCollage(topic, borders, rotations, filter, letters);
				HttpSession session = request.getSession();
				
				session.setAttribute("MainCollage", topicCollage);
				ArrayList<Collage> previousList = createNewListForTesting();
				session.setAttribute("PreviousCollageList", previousList);
				
				RequestDispatcher view = request.getRequestDispatcher("/CollageViewerPage.jsp");
				view.forward(request, response);

				return;
    }

	//builds collage with String paramater topic
	public Collage buildCollage(String topic, boolean borders, boolean rotations, String filter, String letters){
		CollageHandler ch = new CollageHandler(topic, borders, rotations, filter, letters);
		Collage c = ch.build();
		return c;

	}
	
	
	
	public ArrayList<Collage> createNewListForTesting() {
		ArrayList<Collage> testList = new ArrayList<Collage>();
		return testList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Collage> getPreviousCollageList(HttpSession session) {
		ArrayList<Collage> previousCollageList = new ArrayList<Collage>();
		previousCollageList = (ArrayList<Collage>) session.getAttribute("PreviousCollageList");
		return previousCollageList;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
