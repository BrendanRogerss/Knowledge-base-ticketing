package Controllers;

import Models.Issue;

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
@WebServlet(urlPatterns = {"/ReportedIssue"})
public class ReportedIssues extends HttpServlet{

    ArrayList<Issue> issues = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/issueList.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //copied this shit from uonSales

        // The user has come here by mistake.
        // It is possible that they have tried logging in after trying to buy an item and being shown the error message
        // Is this a valid assumption? if not you might need to do something else here
        //response.sendRedirect("index.jsp");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/issueList.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
    }

}
