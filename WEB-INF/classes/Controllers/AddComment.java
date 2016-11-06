package Controllers;

import Models.User;

import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet used to add a comment to database
 * also changes the issue state depending on the comment type
 */

@WebServlet(urlPatterns = {"/AddComment"})
public class AddComment extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set current page and reset success and error messages
        request.getSession().setAttribute("currentPage", "addComment");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //check is user exists in the session and if logged in
        //else redirect
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.isLoggedIn()){
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        //check form validation
        //else redirect to HomePage with error message
        if(!validateForm(request)) {
            response.sendRedirect("HomePage");
            return;
        }

        //get issueID and setup variables to be used
        String issueID = request.getParameter("issueID");
        int numOfComments = 0;
        Database database = new Database();

        if(checkIfIssueResolved(issueID)){
            request.getSession().setAttribute("error", "Issue already resolved");
            response.sendRedirect("HomePage");
            return;
        }

        //count all existing comments for new comment ID
        try{

            //setup connection
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT COUNT(*) FROM UserComment"; //count all comments
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                numOfComments = result.getInt(1);
            }

            //prepare statement and insert new comment into database
            query = "INSERT INTO UserComment VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, numOfComments+1);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String stringDate = dateFormat.format(date);
            prepStatement.setString(2, stringDate);
            prepStatement.setString(3, request.getParameter("commentContent"));
            prepStatement.setString(4, request.getParameter("commentType"));
            prepStatement.setString(5, user.getUsername());
            prepStatement.setString(6, request.getParameter("issueID"));

            prepStatement.executeUpdate();

            //close resources
            connection.close();
            result.close();


        }catch (Exception e) {
            String error = "Something went wrong when adding comment: "; //set an error
            request.setAttribute("error", error+e.getMessage());
        }

        //if staff commented, add notification to issue and change issue state to respective type in form
        if(user.isStaff()) {
            database.addNotification(issueID);
            database.changeIssueState(request.getParameter("issueID"), request.getParameter("state"));
        }

        //redirect back to Issue
        response.sendRedirect("Issue?issueID="+request.getParameter("issueID"));

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //Used to test form parameters
    //Pre: HttpServlet request object with form parameters in it
    //Post: boolean if form is valid or not
    private boolean validateForm(HttpServletRequest request){

        String error = null;
        //if content was added at all
        if(request.getParameter("commentContent") == null || request.getParameter("commentContent").length() == 0)
            error = "No comment entered.";
        //else if length exceeds max size in database
        else if(request.getParameter("commentContent").length() > 1000)
            error = "Comment exceeds maximum length.";

        //set error in sessoin object if thrown
        if(error != null){
            request.getSession().setAttribute("error", error);
            return false;
        }

        //return true if form is valid
        return true;


    }

    private boolean checkIfIssueResolved(String issueID){

        //query
        String queryString = "SELECT COUNT(*) FROM Issue WHERE issueID = '" + issueID + "' " +
                "AND state = 'Resolved'";

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
