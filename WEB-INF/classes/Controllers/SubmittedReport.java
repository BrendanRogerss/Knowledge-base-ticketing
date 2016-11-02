package Controllers;

import Models.User;

import javax.activation.DataSource;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.*;
import javax.naming.InitialContext;

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


        ////////////////////////////////////////////
        System.out.println("starting try");
        ////////////////////////////////////////////


        try {


            //get connection details, from context.xml I take it
            javax.sql.DataSource datasource = (javax.sql.DataSource)new
                    InitialContext().lookup("java:/comp/env/SENG2050");
            //establish connection
            Connection connection = datasource.getConnection();

            ////////////////////////////////////////////
            System.out.println("Attempting Count");
            ////////////////////////////////////////////


            //get current amount of issues in database for new issue number
            statement = "SELECT COUNT(issueID) FROM Issue";
            prepStatement = connection.prepareStatement(statement);
            System.out.println("attempting to execute");
            ResultSet rs = prepStatement.executeQuery();
            System.out.println("executed");
            int numOfIssues = 1;
            if(rs == null) System.out.println("Resultset is NULL");
            if(rs.next())
                 numOfIssues = rs.getInt(1);

            ////////////////////////////////////////////
            System.out.println("Preparing ");
            ////////////////////////////////////////////


            //preparing new issue insert statement with all request data from form
            statement = "INSERT INTO Issue(issueID, state, category, title, description, " +
                    " location, browser, website, internalAccess, alternateBrowser, computerRestatart" +
                    "errorMessage, resolutionDetails, reportDateTime, resolvedDateTime, username)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ////////////////////////////////////////////
            System.out.println(request.getParameter("description"));
            ////////////////////////////////////////////



            prepStatement = connection.prepareStatement(statement);
            prepStatement.setInt(1, numOfIssues + 1);
            prepStatement.setString(2, "New");
            prepStatement.setString(3, request.getParameter("category"));
            prepStatement.setString(4, request.getParameter("title"));
            prepStatement.setString(5, request.getParameter("description"));
            prepStatement.setString(6, request.getParameter("location"));
            prepStatement.setString(7, request.getParameter("browser"));
            prepStatement.setString(8, request.getParameter("website"));
            prepStatement.setString(9, request.getParameter("internalAccess"));
            prepStatement.setString(10, request.getParameter("alternateBrowser"));
            prepStatement.setString(11, request.getParameter("computerRestart"));
            prepStatement.setString(12, request.getParameter("errorMessage"));
            prepStatement.setString(13, "");
            //formatting current date and time
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String stringDate = dateFormat.format(date);

            prepStatement.setString(14, stringDate);
            prepStatement.setString(15, null);
            prepStatement.setString(16, user.getUsername());
            //execution.
            prepStatement.executeUpdate();

            ////////////////////////////////////////////
            System.out.println("Report Submitted");
            ////////////////////////////////////////////

        } catch (SQLException e) {
            //TODO: set error tag in the session
            ////////////////////////////////////////////
            System.out.println(e.getMessage());
            ////////////////////////////////////////////
        } catch (NamingException e) {
            System.err.println("......NamingException......");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        //TODO: Work out where to redirect
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

}
