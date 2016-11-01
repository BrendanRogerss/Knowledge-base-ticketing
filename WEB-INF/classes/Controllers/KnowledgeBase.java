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

@WebServlet(urlPatterns = {"/KnowledgeBase"})
public class KnowledgeBase extends HttpServlet{

    private ArrayList<Issue> issues;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //build list
        issues = new ArrayList<>();
        for (int i = 1; i <= 10; i++) { //temp list to use till sql gets set up
            Issue issue = new Issue();
            issue.setIssueID(i*10000);
            issue.setStatus(Integer.toString(i));
            issue.setCategory(Integer.toString(i));
            issue.setReportedDateTime(new Date());
            issue.setUser(Integer.toString(i));
            issues.add(issue);
        }

        request.setAttribute("list", issues); //add the list to the session
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/issueList.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //copied this shit from uonSales

        // The user has come here by mistake.
        // It is possible that they have tried logging in after trying to buy an item and being shown the error message
        // Is this a valid assumption? if not you might need to do something else here
        doPost(request, response);
    }

}
