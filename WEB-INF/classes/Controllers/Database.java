package Controllers;

import Models.Issue;
import Models.Notification;
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
                //dont really need this here anymore since its now stored in the sql
                issue.setNotification(checkIssueNotifications(result.getInt(1)));

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

    public ResultSet queryYeahLetsNotUse(String q) {
        ResultSet result = null;
        try {
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(q);

            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void addNotification(Notification notification) {
        int noteCount = 0;
        String queryString = "SELECT COUNT(*) FROM Notification";
        try {

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queryString);


            if (result.next())
                noteCount = result.getInt(1);


            String queryS = "INSERT INTO Notification VALUE (?, ?, ?, ?, ?)";


            PreparedStatement prepStatement = connection.prepareStatement(queryS);
            prepStatement.setInt(1, noteCount);
            prepStatement.setString(2, notification.getUsername());
            prepStatement.setInt(3, notification.getIssueID());
            prepStatement.setString(4, notification.getContent());
            prepStatement.setBoolean(5, false);
            prepStatement.executeUpdate();

            System.out.println(notification.getContent());

            connection.close();
            result.close();
        } catch (Exception e) {
            //TODO: Do we have to handle errors in the database class?
            System.out.println(e.getMessage());
        }
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
            System.out.println("notification seen: "+e.getMessage());
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

    public boolean checkIssueNotifications(int issueID){
        String queryString = "SELECT COUNT(*) FROM Notification WHERE issueID = '" + issueID + "' " +
                "AND seen = false";
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
        return check;
    }


}
