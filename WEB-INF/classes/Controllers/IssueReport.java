package Controllers;

import Models.Issue;
import Models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Brendan on 19/10/2016.
 */
@WebServlet(urlPatterns = {"/ReportIssue"})
public class IssueReport extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");


        Issue issue = new Issue();
        /*
        issue.setUser(user.getUsername());
        issue.setContent(request.getParameter("content"));
        issue.setStatus("whateverTheStartingStatusIs");
        issue.setCategory(request.getParameter("category"));
        issue.setTitle(request.getParameter("title"));
        issue.setDescription(request.getParameter("description"));
        issue.setReportedDateTime(new Date());

        issue.setIssueID(0); //need to set it to the total number of issues + 1
        */

        issue.setIssueID(0); //till we get the form working
        issue.setStatus("status");
        issue.setCategory("category");
        issue.setReportedDateTime(new Date());
        issue.setUser("user");
        //add this shit to sql database


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //copied this shit from uonSales

        // The user has come here by mistake.
        // It is possible that they have tried logging in after trying to buy an item and being shown the error message
        // Is this a valid assumption? if not you might need to do something else here
        response.sendRedirect("index.jsp");
    }

}
