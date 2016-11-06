package Controllers;

import Models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet used to change the state of the issue
 * used when a user comments, proposes solution or manually sets state
 */
@WebServlet(urlPatterns = {"/ChangeIssueState"})
public class ChangeIssueState extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set current page and reset success and error messages
        request.getSession().setAttribute("currentPage", "changeIssueState");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //check is user exists in the session and if logged in
        //else redirect
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isLoggedIn()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        if(request.getParameter("issueID") == null || request.getParameter("issueID").equals("")){
            request.getSession().setAttribute("error", "Issue not found");
            response.sendRedirect("HomePage");
            return;
        }

        //change issue state to whatever is passed into the form
        Database database = new Database();
        database.changeIssueState(request.getParameter("issueID"), request.getParameter("state"));

        //chose which servlet to redirect back to depending what's passed in from form
        if (request.getParameter("path") != null) {
            if (request.getParameter("path").equals("knowledgeBase")) {
                response.sendRedirect(getServletContext().getContextPath() + "/KnowledgeBase");
            } else if (request.getParameter("path").equals("reportedIssues")) {
                response.sendRedirect(getServletContext().getContextPath() + "/ReportedIssues");
            } else if (request.getParameter("path").equals("completedIssues")) {
                response.sendRedirect(getServletContext().getContextPath() + "/CompletedIssues");
            }
        }
                else if (request.getParameter("issueList") != null)
            response.sendRedirect(getServletContext().getContextPath() + "/ReportedIssues");
        else
            response.sendRedirect(getServletContext().getContextPath() + "/Issue?issueID=" + request.getParameter("issueID"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
