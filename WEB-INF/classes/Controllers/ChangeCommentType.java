package Controllers;

import Models.Comment;
import Models.Issue;
import Models.Notification;
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
@WebServlet(urlPatterns = {"/ChangeCommentType"})
public class ChangeCommentType extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("currentPage", "changeCommentType");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isLoggedIn()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        Database database = new Database();
        database.changeCommentType(request.getParameter("commentID"), request.getParameter("commentType"));

        if(request.getParameter("commentType").equals("Rejected")) {
            String stateSet = "";
            if(checkIfProposedComment(request.getParameter("issueID")))
                stateSet = "Completed";
            else stateSet = "In-Progress";
            database.changeIssueState(request.getParameter("issueID"), stateSet);
        }

        else if(request.getParameter("commentType").equals("Accepted"))
            database.changeIssueState(request.getParameter("issueID"), "Resolved");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Issue?issueID="+request.getParameter("issueID"));
        dispatcher.forward(request, response);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        //nothing
    }

    private boolean checkIfProposedComment(String issueID){
        String queryString = "SELECT COUNT(*) FROM UserComment WHERE issueID = '" + issueID + "' " +
                "AND commentType = 'Proposed'";

        boolean check = false;
        //read all from result set. set notification object and add to list
        try {
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryString);

            if(rs.next() && rs.getInt(1) > 0)
                check = true;
            else check = false;
            rs.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
