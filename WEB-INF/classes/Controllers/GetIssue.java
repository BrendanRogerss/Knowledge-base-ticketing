package Controllers;

import Models.Comment;
import Models.Issue;
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
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Brendan on 19/10/2016.
 *
 */
//gets all the information for an issue and associated comments and redirects to the view issue jsp
@WebServlet(urlPatterns = {"/Issue"})
public class GetIssue extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //set information for hte page in the session
        request.getSession().setAttribute("currentPage", "getIssue");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //check if the user is logged in
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.isLoggedIn()){
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        //check if the user has any notifications
        Database database = new Database();
        database.checkNotifications(request.getSession());

        //get issue id from request
        String issueID = request.getParameter("issueID");
        ArrayList<Comment> comments = new ArrayList<>();
        //System.out.println("before getIssueQuery");
        String query = "SELECT * FROM Issue WHERE issueID = '" + issueID + "'"; //query for the issue with matching id

        ArrayList<Issue> issues = database.getIssues(query);//return a list containing one issue
        //check if the query returned any results
        if(issues.size()==0){
            request.getSession().setAttribute("error", "Issue not found");
            //redirect to the home page
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/HomePage"); //redirect back to homepage
            dispatcher.forward(request, response);
            return;
        }
        Issue issue = issues.get(0);


        //get all comments associated with that issue
        try{

            ResultSet result = null;
            if(issue.getState().equals("KnowledgeBase")){ //if the issue is for knowledge base, dont get all the comments
                query = "SELECT * FROM UserComment WHERE issueID = '" + issueID +
                        "' AND commentType = 'Accepted' OR commentType = 'Proposed'";
            }
            else
                query = "SELECT * FROM UserComment WHERE issueID = '" + issueID + "'"; //otherwise get them all

            javax.sql.DataSource datasource = (javax.sql.DataSource) new
                    InitialContext().lookup("java:/comp/env/SENG2050");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);

            //add the comments to the list
            while(result.next()) {
                Comment comment = new Comment();
                comment.setCommentID(result.getInt(1));
                comment.setSubmissionDateTime(formatDate(result.getString(2)));
                comment.setContent(result.getString(3));
                comment.setCommentType(result.getString(4));
                comment.setUsername(result.getString(5));
                comment.setIssueID(result.getInt(6));
                comments.add(comment);
            }

            result.close();
            connection.close();

            //add the final solution to the issue if its knowledgebase
            if(issue.getState().equals("KnowledgeBase")) {
                for(Comment comment : comments){
                    if(comment.getCommentType().equals("Accepted")){
                        comments = new ArrayList<Comment>();
                        comments.add(comment);
                        break;
                    }
                }
            }

            issue.setComments(comments);

            //check if the person requesting the issue has permission
            if(!user.getUsername().equals(issue.getUsername()) && !user.isStaff() && !issue.getState().equals("knowledgeBase") && !issue.getState().equals("KnowledgeBase")){
                request.getSession().setAttribute("error", "Permission not valid for the issue");
                response.sendRedirect("HomePage");
                return;
            }

            request.setAttribute("issue", issue); //pass the issue into the database



            //if the user has a notification tied with the current issue
            if (user.getUsername().equals(issue.getUsername())) {
                database.setNotificationToSeen(issueID);//set it to seen
            }

            database.checkNotifications(request.getSession());

        }catch (Exception e) {
            String error = "Something went wrong in Get Issue:"; //set an error
            request.getSession().setAttribute("error", error+e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewIssue.jsp"); //redirect back to homepage
        dispatcher.forward(request, response); //might be better off redirecting back to issue list
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //returns a formatted string of the date
    private Date formatDate(String str) throws java.text.ParseException
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = dateFormat.parse(str);
        return date;
    }
}
