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
import java.util.Date;

/**
 * Created by Brendan on 19/10/2016.
 */
@WebServlet(urlPatterns = {"/ReportIssue"})
public class IssueReport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        try {
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();

            String statement = "SELECT COUNT(*) FROM Issue AS numOfIssues;";
            PreparedStatement s = connection.prepareStatement(statement);
            ResultSet rs = s.executeQuery();

            int numOfIssues = rs.getInt("numOfIssues");

            /*
            Issue issue = new Issue();

            issue.setUser(user.getUsername());
            issue.setContent(request.getParameter("content"));
            issue.setStatus("whateverTheStartingStatusIs");
            issue.setCategory(request.getParameter("category"));
            issue.setTitle(request.getParameter("title"));
            issue.setDescription(request.getParameter("description"));
            issue.setReportedDateTime(new Date());

            issue.setIssueID(numOfIssues + 1); //need to set it to the total number of issues + 1
            */

            ///////////////////////needs rest of attributes ///////////////////////////

            statement = "INSERT INTO Issue(issueID, content, state....) VALUES (?, ?, ?, .........)";
            s = connection.prepareStatement(statement);
            s.setInt(1, numOfIssues + 1);
            s.setString(2, request.getParameter("content"));
            s.setString(3, "whateverTheStartingStatusIs");
            ////////////////////////rest of the attributes ////////////////////////
            s.executeUpdate();


        } catch (Exception e){
            ////////////////////// set error tag in the session //////////////////////////////////////////
        }

        //////////////////redirect/////////////////////////
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

}
