package utils;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

/**
 * Log the contents of the body. Could be used to handle errors etc.
 */
public class NotLoggedIn extends TagSupport {

    @Override
    public int doStartTag() {
        try{
            JspWriter out = pageContext.getOut();
            out.print("Hello bitches");
        }
        catch (IOException ioe){
            System.out.println("Error in NotLoggedIn: " + ioe);
        }
        return SKIP_BODY;
    }

}


