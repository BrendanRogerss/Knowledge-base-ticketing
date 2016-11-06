package Controllers;


import javax.servlet.annotation.WebServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * servlet used only to forward to Contact jsp
 */
@WebServlet(urlPatterns = {"/Contact"})
public class Contact extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set current page and reset success and error messages
        request.getSession().setAttribute("currentPage", "contact");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //forward
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/contact.jsp"); //redirect to jsp
        dispatcher.forward(request, response);
        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
