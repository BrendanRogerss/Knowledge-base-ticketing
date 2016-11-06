package Controllers;

import Models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//This servlet redirects the user to the reportIssue.jsp
@WebServlet(urlPatterns = {"/ReportIssue"})
public class ReportIssue extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set all the information in the session
        request.getSession().setAttribute("currentPage", "reportIssue");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //check if the user has logged in
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.isLoggedIn()){
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        //check if the user has gotten any notifications
        Database database = new Database();
        database.checkNotifications(request.getSession());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/reportIssue.jsp"); //redirect to report issue
        dispatcher.forward(request, response);
        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
