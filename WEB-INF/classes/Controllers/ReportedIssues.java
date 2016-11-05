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
@WebServlet(urlPatterns = {"/ReportedIssues"})
public class ReportedIssues extends HttpServlet {

    ArrayList<Issue> issues;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("currentPage", "reportedIssues");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);



        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isLoggedIn()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        Database database = new Database();
        database.checkNotifications(request.getSession());

        issues = new ArrayList<>();
        //String userID = user.getUsername();

        String query;
        String sortString = request.getParameter("sortString");
        if (sortString == null) sortString = "state";
        if (user.isStaff()) {
            query = "SELECT * FROM Issue WHERE state!='KnowledgeBase' OR state!='Completed' " +
                    "ORDER BY " + sortString;
        } else {
            query = "SELECT * FROM Issue WHERE username='" + user.getUsername() + "' AND (state!='KnowledgeBase') " +
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
