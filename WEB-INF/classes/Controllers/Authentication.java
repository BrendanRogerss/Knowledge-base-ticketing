package Controllers;

import Models.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Brendan on 19/10/2016.
 */

@WebServlet(urlPatterns = {"/Authentication"})
public class Authentication extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get details
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        User user = (User) request.getSession().getAttribute("user");

        HttpSession session = request.getSession();
        String statement, redirectLocation = "index.jsp";
        User dbUser = new User();
        PreparedStatement prepStatement;

        user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        try{
            //get connection details, from context.xml I take it
            javax.sql.DataSource dataSource = (javax.sql.DataSource)new InitialContext().lookup("java:/comp/env/SENG2050");
            //establish connection
            Connection connection = dataSource.getConnection();

            //get current amount of issues in database for new issue number
            statement = "SELECT * FROM Users WHERE username = '" + request.getParameter("username") + "'";
            prepStatement = connection.prepareStatement(statement);
            ResultSet rs = prepStatement.executeQuery();

            if(rs.next()){
                dbUser.setUsername(rs.getString(1));
                dbUser.setPassword(rs.getString(2));
                dbUser.setType(rs.getString(3));
                dbUser.setFirstName(rs.getString(4));
                dbUser.setLastName(rs.getString(5));
                dbUser.setEmail(rs.getString(6));
                dbUser.setContactNumber(rs.getString(7));
            }

            connection.close();
            rs.close();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            session.setAttribute("error", "Couldn't connect");
            response.sendRedirect(redirectLocation);
            return;
        } catch (NamingException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            session.setAttribute("error", "Naming exception");
            response.sendRedirect(redirectLocation);
            return;
        }

        //test user credentials
        if((dbUser.getUsername() == null || dbUser.getPassword() == null) || !user.getPassword().equals(dbUser.getPassword()))
        {
            user.setLoggedIn(false);
            redirectLocation = "index.jsp";
            session.setAttribute("error", "Incorrect credentials"); //Doesnt work with redirects
        }
        else if(user.getPassword().equals(dbUser.getPassword())){
            user = dbUser;
            user.setLoggedIn(true);
            redirectLocation = "HomePage";
            session.setAttribute("user", user);

            Database database = new Database(); //make a new object
            database.checkNotifications(request.getSession()); //get all the notifications for the user and set it to the session
        }



        response.sendRedirect(redirectLocation);
        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
