package Controllers;

import Models.Comment;
import Models.Issue;
import Models.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Brendan on 19/10/2016.
 */
@WebServlet(urlPatterns = {"/ChangeIssueState"})
public class ChangeIssueState extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("currentPage", "changeIssueState");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isLoggedIn()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        Database database = new Database();
        database.changeIssueState(request.getParameter("issueID"), request.getParameter("state"));

        /*if(request.getParameter("state").equals("Resolved")){ //set the resolved date for an issue
            database.setIssueResolvedDate(request.getParameter("issueID"));
        }*/

        if (request.getParameter("path") != null) {
            if (request.getParameter("path").equals("knowledgeBase")) {
                response.sendRedirect(getServletContext().getContextPath() + "/KnowledgeBase");
            } else if (request.getParameter("path").equals("reportedIssues")) {
                response.sendRedirect(getServletContext().getContextPath() + "/ReportedIssues");
            } else if (request.getParameter("path").equals("completedIssues")) {
                response.sendRedirect(getServletContext().getContextPath() + "/CompletedIssues");
            } else {
                System.out.println("test test test");
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
