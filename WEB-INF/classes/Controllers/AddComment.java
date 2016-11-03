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
@WebServlet(urlPatterns = {"/AddComment"})
public class AddComment extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String issueID = request.getParameter("issueID");
        User user = (User) request.getSession().getAttribute("user");
        int numOfComments = 0;

        try{ //get all the comments

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT COUNT(*) FROM UserComment"; //query for all the comments for that issue
            ResultSet result = statement.executeQuery(query);

            if(result.next()){
                numOfComments = result.getInt(1);
            }

            query = "INSERT INTO UserComment VALUES (?, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, numOfComments+1);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String stringDate = dateFormat.format(date);
            prepStatement.setString(2, stringDate);
            prepStatement.setString(3, request.getParameter("commentContent"));
            prepStatement.setString(4, user.getUsername());
            prepStatement.setString(5, request.getParameter("issueID"));

            prepStatement.executeUpdate();

            //TODO: Have to add the issueID to the request object????

        }catch (Exception e) {
            String error = "Something went wrong in GetIssue"; //set an error
            request.setAttribute("error", error);
        }

        response.sendRedirect("/GetIssue");

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
