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

/**
 * Created by Brendan on 19/10/2016.
 */

@WebServlet(urlPatterns = {"/Authentication"})
public class Authentication extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get details

        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.isLoggedIn()){

        }

        HttpSession session = request.getSession();
        String statement, redirectLocation = "index.jsp";
        String dbUsername = null, dbPassword = null;
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
            statement = "SELECT username, pass FROM Users WHERE username = '" + request.getParameter("username") + "'";
            prepStatement = connection.prepareStatement(statement);
            ResultSet rs = prepStatement.executeQuery();

            if(rs.next()){
                dbUsername = rs.getString(1);
                dbPassword = rs.getString(2);
            }

            connection.close();
            rs.close();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (NamingException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        //test user credentials
        if((dbUsername == null || dbPassword == null) || !user.getPassword().equals(dbPassword))
        {
            user.setLoggedIn(false);
            redirectLocation = "index.jsp";
            request.setAttribute("error", "Incorrect credentials"); //Doesnt work with redirects
        }
        else if(user.getPassword().equals(dbPassword)){
            user.setLoggedIn(true);
            user.setType("staff"); //TODO: get and set the type of user in database and shit
            redirectLocation = "HomePage";
            session.setAttribute("user", user);
        }

        //TODO: change this to redirect instead of forward
        response.sendRedirect(redirectLocation);

        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
