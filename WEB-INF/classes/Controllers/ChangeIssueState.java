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


        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isLoggedIn()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        try {

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            String query = "UPDATE Issue SET state = ? WHERE issueID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, request.getParameter("state"));
            prepStatement.setString(2, request.getParameter("issueID"));
            prepStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            String error = "Something went wrong when updating Issue State "; //set an error
            request.setAttribute("error", error + e.getMessage());
        }

        if(request.getParameter("state").equals("Complete")) {

            //TODO need to the add comment, given by parameter "solution". Not sure if AddComment?issueID is how we handle this idk
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddComment?issueID="+request.getParameter("issueID"));
            dispatcher.forward(request, response);
            request.setAttribute("commentContent", request.getParameter("commentContent"));
            request.setAttribute("commentType", request.getParameter("commentType"));
        }
        else if (request.getParameter("issueList").equals("true")){

            response.sendRedirect(getServletContext().getContextPath() + "/ReportedIssues");
        }
        else
            response.sendRedirect(getServletContext().getContextPath() + "/Issue?issueID=" + request.getParameter("issueID"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
