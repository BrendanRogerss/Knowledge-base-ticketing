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

//Use this servlet to show all the reported issues for a user
//shows all non-completed issues if the user is staff
//shows only issues user has reported if student
@WebServlet(urlPatterns = {"/ReportedIssues"})
public class ReportedIssues extends HttpServlet {

    ArrayList<Issue> issues;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //set all the necessary information in the session
        request.getSession().setAttribute("currentPage", "reportedIssues");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //check if the user has logged in
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isLoggedIn()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        //check if the user has any new notifications
        Database database = new Database();
        database.checkNotifications(request.getSession());

        issues = new ArrayList<>();

        String query;
        String sortString = request.getParameter("sortString"); //get the category to sort by
        if (sortString == null) sortString = "state";
        //change query if the user is staff or student
        if (user.isStaff()) {
            query = "SELECT * FROM Issue WHERE (state <> 'KnowledgeBase' AND state <> 'Completed' AND state <> 'Resolved') OR " +
                    "(state = 'Resolved' AND username = '" +user.getUsername() + "') ORDER BY " + sortString;
        } else {
            query = "SELECT * FROM Issue WHERE username='" + user.getUsername() + "' AND (state <> 'KnowledgeBase') " +
                    "ORDER BY " + sortString;
        }
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
