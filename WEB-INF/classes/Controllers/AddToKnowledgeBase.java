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
@WebServlet(urlPatterns = {"/AddToknowledgeBase"})
public class AddToKnowledgeBase extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isLoggedIn()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        String issueID = request.getParameter("issueID");

        try { //get all the comments

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            String query = "UPDATE Issue SET state = 'knowledgeBase' WHERE issueID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, issueID);

            prepStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            String error = "Something went wrong when adding issue to knowledgebase: "; //set an error
            request.setAttribute("error", error + e.getMessage());
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ReportedIssues?issueID=" + request.getParameter("issueID")); //redirect back to homepage
        dispatcher.forward(request, response); //might be better off redirecting back to issue list
        response.sendRedirect(getServletContext().getContextPath() + "/ReportedIssues?issueID=" + request.getParameter("issueID"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
