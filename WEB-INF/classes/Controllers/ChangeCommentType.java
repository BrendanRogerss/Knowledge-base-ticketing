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
 * servlet used to change the comment type. only used for accepting and rejecting solution comments
 */
@WebServlet(urlPatterns = {"/ChangeCommentType"})
public class ChangeCommentType extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set current page and reset success and error messages
        request.getSession().setAttribute("currentPage", "changeCommentType");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //check is user exists in the session and if logged in
        //else redirect
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isLoggedIn()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        //change the comment type
        Database database = new Database();
        database.changeCommentType(request.getParameter("commentID"), request.getParameter("commentType"));

        //set the issue state depending on what new comment type is
        if(request.getParameter("commentType").equals("Rejected")) { //if rejected
            String stateSet = "";
            if(checkIfProposedComment(request.getParameter("issueID"))) // if any more solutions are currents proposed
                stateSet = "Completed";
            else stateSet = "In-Progress"; //else set back to in progress
            database.changeIssueState(request.getParameter("issueID"), stateSet);
        }
        //else if comment type is accepted set to resolved and set resolved date time on issue
        else if(request.getParameter("commentType").equals("Accepted")) {
            database.changeIssueState(request.getParameter("issueID"), "Resolved");
            database.setIssueResolvedDate(request.getParameter("issueID"));
        }

        //redirect back to issue
        response.sendRedirect("Issue?issueID="+request.getParameter("issueID"));

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        //nothing
    }

    //checks databse to see if any proposed solutions still exist
    //Pre: issueID to check against
    //Post: boolean if any still exist
    private boolean checkIfProposedComment(String issueID){

        //query
        String queryString = "SELECT COUNT(*) FROM UserComment WHERE issueID = '" + issueID + "' " +
                "AND commentType = 'Proposed'";

        boolean check = false;
        try {
            //setup connection
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryString);

            //if any comments exist return true
            if(rs.next() && rs.getInt(1) > 0)
                check = true;
            else check = false;

            //close resources
            rs.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

}
