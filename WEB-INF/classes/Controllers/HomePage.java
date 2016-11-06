package Controllers;

import Models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Brendan on 20/10/2016.
 */

//sends the user to the home page
@WebServlet(urlPatterns = {"/HomePage"})
public class HomePage extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //set the information for the page in the session
        request.getSession().setAttribute("currentPage", "homepage");

        //check if the user has logged in
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.isLoggedIn()){
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        //check if the user has any notifications
        Database database = new Database();
        database.checkNotifications(request.getSession());

        //redirect to the home page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/homepage.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
