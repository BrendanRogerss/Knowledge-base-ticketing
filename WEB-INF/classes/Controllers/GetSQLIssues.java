package Controllers;

import Models.Issue;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
                Issue issue = new Issue();
                //issueID = result.getString(1)
                String content = result.getString(2);
                //get all the content of the issue and put it into the object


                issues.add(issue);
            }
        } catch (Exception e) {
            //throw some shit
        }
        return issues;
    }

}
