package Controllers;


import javax.servlet.annotation.WebServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//About servlet is used only to forward to the about.jsp page
@WebServlet(urlPatterns = {"/About"})
public class About extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set current page and reset success and error messages
        request.getSession().setAttribute("currentPage", "About");
        request.getSession().setAttribute("error", null);
        request.getSession().setAttribute("success", null);

        //forward
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/about.jsp");
        dispatcher.forward(request, response);
        return;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
