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
public class GetSQLIssues {

    public GetSQLIssues(){

    }

    public ArrayList<Issue> getIssues(String query) {
        ArrayList<Issue> issues = new ArrayList<>();

        try {
            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query); //connect to the database

            while (result.next()) {
                //get all the content of the issue and put it into the object
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
                issue.setReportedDateTime(formatDate(result.getString(14)));
                issue.setResolvedDateTime(formatDate(result.getString(15)));
                issue.setUsername(result.getString(16));

                issues.add(issue);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return issues;
    }

    private Date formatDate(String str) throws java.text.ParseException
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = dateFormat.parse(str);
        return date;
    }

}
