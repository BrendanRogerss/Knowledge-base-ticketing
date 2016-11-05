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

    public void addNotification(String issueID){
        try {
            String query = "UPDATE Issue SET notification = TRUE WHERE issueID = ?";

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, issueID);
            prepStatement.executeUpdate();
            connection.close();
            System.out.println("added notification, issueid = "+ issueID);
        } catch (Exception e) {
            System.out.println("add notification: "+e.getMessage());
        }
    }

    //sets the number of notifications for the uesr in the session
    public void checkNotifications(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String query = "SELECT * FROM Issue WHERE username='" + user.getUsername() + "'";;
        int i = 0;
        ArrayList<Issue> issues = getIssues(query);
        for(Issue issue: issues){
            if(issue.hasNotification()){
                i++;
            }
        }
        //add list to session object
        session.setAttribute("notificationCount", i);
    }

    public void changeIssueState(String issueID, String state) {
        try {

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            String query = "UPDATE Issue SET state = ? WHERE issueID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, state);
            prepStatement.setString(2, issueID);
            prepStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeCommentType(String commentID, String commentType) {
        try {

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            String query = "UPDATE UserComment SET commentType = ? WHERE commentID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, commentType);
            prepStatement.setString(2, commentID);
            prepStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
        }
    }

    public void setIssueResolvedDate(String date, String issueID){
        try {

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            String query = "UPDATE Issue SET resolvedDateTime = ? WHERE issueID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, date);
            prepStatement.setString(2, issueID);
            prepStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            System.out.println("updating resolved time: "+e.getMessage());
        }
    }




}
