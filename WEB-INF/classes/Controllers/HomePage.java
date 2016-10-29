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

@WebServlet(urlPatterns = {"/HomePage"})
public class HomePage extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if(!user.isLoggedIn()){ //check if the user is logged in
            request.setAttribute("error", "You need to be logged in to do that."); //put an error message into the session

            String whereToSendThem = "index.jsp";

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(whereToSendThem); //redirect the user
            dispatcher.forward(request, response);
            return;
        }else{
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("WEB-INF/jsp/homePage.jsp"); //redirect the user
            dispatcher.forward(request, response);
            return;
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //copied this shit from uonSales

        // The user has come here by mistake.
        // It is possible that they have tried logging in after trying to buy an item and being shown the error message
        // Is this a valid assumption? if not you might need to do something else here
        response.sendRedirect("index.jsp");
    }
}
