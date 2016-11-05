package Controllers;

import Models.Issue;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Brendan on 1/11/2016.
 */
public class Database {

    public Database(){

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

    public ResultSet query(String q){
        ResultSet result = null;
        try {
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(q);

        }catch (Exception e){
            //TODO: add something in here
        }
        return result;
    }



    /*private Date formatDate(String str) throws java.text.ParseException
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = dateFormat.parse(str);
        return date;
    }*/

}
