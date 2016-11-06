package Controllers;

import Models.Issue;
import Models.User;

import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Brendan on 1/11/2016.
 */
public class Database {

    public Database() {

    }

    public ArrayList<Issue> getIssues(String query) {
        ArrayList<Issue> issues = new ArrayList<>();

        try {
            //System.out.println("database start");
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");
            // System.out.println("after datasource");
            Connection connection = datasource.getConnection();
            // System.out.println("after connection");
            Statement statement = connection.createStatement();
            // System.out.println("after statement");
            ResultSet result = statement.executeQuery(query); //connect to the database
            // System.out.println("after result");


            while (result.next()) {
                //get all the content of the issue and put it into the object
                //System.out.println("start loop");
                Issue issue = new Issue();
                issue.setIssueID(result.getInt(1));
                issue.setState(result.getString(2));
                issue.setCategory(result.getString(3));
                issue.setTitle(result.getString(4));
                issue.setDescription(result.getString(5));
                issue.setLocation(result.getString(6));
                issue.setBrowser(result.getString(7));
                issue.setWebsite(result.getString(8));
                issue.setInternalAccess(result.getBoolean(9));
                issue.setAlternateBrowser(result.getBoolean(10));
                issue.setComputerRestart(result.getBoolean(11));
                issue.setErrorMessage(result.getString(12));
                issue.setResolutionDetails(result.getString(13));
                issue.setReportedDateTime(result.getString(14));
                issue.setResolvedDateTime(result.getString(15));
                issue.setUsername(result.getString(16));
                issue.setNotification(result.getBoolean(17));

                issues.add(issue);
                // System.out.println("end of loop");
            }

            connection.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //System.out.println("databse end");


        return issues;
    }


    //sets notification to false in whichever issueID is passed in
    public void setNotificationToSeen(String issueID) {
        try {
            String query = "UPDATE Issue SET notification = FALSE WHERE issueID = ?";

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, issueID);
            prepStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("notification to seen: "+e.getMessage());
        }

    }

    //sets the notification boolean to true in whichever issueID is passed in
    public void addNotification(String issueID){
        try {
            System.out.println("IssueID: " + issueID);
            String query = "UPDATE Issue SET notification = TRUE WHERE issueID = ?";

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, issueID);
            prepStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("notification to seen: "+e.getMessage());
        }
    }

    //sets the number of notifications for the uesr in the session
    public void checkNotifications(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String query = "SELECT * FROM Issue WHERE username='" + user.getUsername() + "'";
        int i = 0;
        ArrayList<Issue> issues = getIssues(query);
        for(Issue issue: issues){
            if(issue.hasNotification()){
                i++;
                System.out.println("counted notification");
            }
        }
        //add list to session object
        session.setAttribute("notificationCount", i);
    }


    //used to change the state of an issue
    //Pre: issueID String and new state String
    public void changeIssueState(String issueID, String state) {
        try {
            //setup connection
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            //setup query and execute
            String query = "UPDATE Issue SET state = ? WHERE issueID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, state);
            prepStatement.setString(2, issueID);
            prepStatement.executeUpdate();
            //close resources
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //used to change the type of comment
    //Pre: commentID String, commentType String to set to
    public void changeCommentType(String commentID, String commentType) {
        try {
            //setup connection
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            //setup query and execute
            String query = "UPDATE UserComment SET commentType = ? WHERE commentID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, commentType);
            prepStatement.setString(2, commentID);
            prepStatement.executeUpdate();
            //close resources
            connection.close();

        } catch (Exception e) {
        }
    }

    //used to set the reslvedDate on an issue to current system date and time
    //Pre: isueID String to set to
    public void setIssueResolvedDate(String issueID){

        //get and format current system date and time
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String stringDate = dateFormat.format(date);
        try {
            //setup connection
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            //setup query and exectue update
            String query = "UPDATE Issue SET resolvedDateTime = ? WHERE issueID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, stringDate);
            prepStatement.setString(2, issueID);
            prepStatement.executeUpdate();
            //close resources
            connection.close();

        } catch (Exception e) {
            System.out.println("updating resolved time: "+e.getMessage());
        }
    }




}
