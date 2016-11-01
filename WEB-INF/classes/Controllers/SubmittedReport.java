package Controllers;

import Models.Issue;
import Models.User;

import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import javax.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Brendan on 19/10/2016.
 */
@WebServlet(urlPatterns = {"/SubmittedReport"})
public class SubmittedReport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        String statement;
        PreparedStatement prepStatement;

        try {
            //get connection details, from context.xml I take it
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");
            //establish connection
            Connection connection = datasource.getConnection();

            //get current amount of issues in database for new issue number
            statement = "SELECT COUNT(*) FROM Issue AS numOfIssues;";
            prepStatement = connection.prepareStatement(statement);
            ResultSet rs = prepStatement.executeQuery();
            int numOfIssues = rs.getInt("numOfIssues");

            //prepareing new issue insert statement with all request data from form
            statement = "INSERT INTO Issue(issueID, content, state, category, title, description, " +
                    "resolutiondetails, reportDateTime, solvedDateTime, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            prepStatement = connection.prepareStatement(statement);
            prepStatement.setInt(1, numOfIssues + 1);
            prepStatement.setString(2, request.getParameter("content"));
            prepStatement.setString(3, "whateverTheStartingStatusIs");
            prepStatement.setString(4, request.getParameter("category"));
            prepStatement.setString(5, request.getParameter("title"));
            prepStatement.setString(6, request.getParameter("description"));
            prepStatement.setString(7, null);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String stringDate = dateFormat.format(date);

            prepStatement.setString(8, stringDate);
            prepStatement.setString(9, null);
            prepStatement.setString(10, user.getUsername());
            //execution.
            prepStatement.executeUpdate();


        } catch (SQLException e) {
            //TODO: ////////////////////// set error tag in the session //////////////////////////////////////////
        } catch (NamingException e) {
            System.err.println("......NamingException......");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        //TODO: ////////////////redirect/////////////////////////
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

}
