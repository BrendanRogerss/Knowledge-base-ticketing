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
 * servlet used to get all issues with state completed and forward to IssueList jsp
 */
@WebServlet(urlPatterns = {"/CompletedIssues"})
public class CompletedIssues extends HttpServlet{

    ArrayList<Issue> issues;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set current page and reset success and error messages
        request.getSession().setAttribute("currentPage", "completedIssues");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //check is user exists in the session and if logged in
        //else redirect
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.isLoggedIn()){
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }
        //else if user isn't staff then they shouldn't be able to view
        else if(!user.isStaff()){
            response.sendRedirect(getServletContext().getContextPath()+ "/HomePage");
        }

        //check notifications
        Database database = new Database();
        database.checkNotifications(request.getSession());

        issues = new ArrayList<>();
        String query;
        String sortString = request.getParameter("sortString"); //string used for sorting
        if(sortString == null) sortString = "title"; //default sort is title

        //get all issues where state is completed or resolved
        query = "SELECT * FROM Issue WHERE state='Completed' OR state='Resolved' ORDER BY "+sortString;
        issues = database.getIssues(query);
        request.setAttribute("list", issues); //add the list to the session

        //forward to issueList jsp
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/issueList.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
