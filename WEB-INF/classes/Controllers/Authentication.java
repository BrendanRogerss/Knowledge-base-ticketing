package Controllers;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Brendan on 19/10/2016.
 */

//@WebServlet(urlPatterns = {"/"})
//not sure if this ever gets accessed
public class Authentication extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
