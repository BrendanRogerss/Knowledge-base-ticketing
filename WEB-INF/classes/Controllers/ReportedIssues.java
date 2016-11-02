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
import java.util.Date;

/**
 * Created by Brendan on 19/10/2016.
 */
@WebServlet(urlPatterns = {"/ReportedIssues"})
public class ReportedIssues extends HttpServlet{

    ArrayList<Issue> issues;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        issues = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("user");
        //String userID = user.getUsername();

        //TODO: change selection if either staff or user
        String query = "SELECT * FROM Issue";
        GetSQLIssues database = new GetSQLIssues();
        issues = database.getIssues(query);

        request.setAttribute("list", issues); //add the list to the session
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/issueList.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
