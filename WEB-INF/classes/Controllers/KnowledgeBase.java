package Controllers;

import Models.Issue;
import Models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Brendan on 19/10/2016.
 */

//gets all the reported issues that need to be displayed in the knowledge base
@WebServlet(urlPatterns = {"/KnowledgeBase"})
public class KnowledgeBase extends HttpServlet{

    private ArrayList<Issue> issues;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set information for the page in the session
        request.getSession().setAttribute("currentPage", "knowledgeBase");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //check if the user has logged in
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.isLoggedIn()){
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }
        //check if the user has any notifications
        Database database = new Database();
        database.checkNotifications(request.getSession());

        //build list
        issues = new ArrayList<>();

        //query for the knowledge base aarticles
        String sortString = request.getParameter("sortString");
        if (sortString == null) sortString = "state";
        String query = "SELECT * FROM Issue WHERE state='KnowledgeBase' " +
                "ORDER BY " + sortString;
        issues = database.getIssues(query);

        request.setAttribute("list", issues); //add the list to the session
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/issueList.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
