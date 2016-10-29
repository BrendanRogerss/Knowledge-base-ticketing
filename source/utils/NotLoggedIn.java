package utils;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * Log the contents of the body. Could be used to handle errors etc.
 */
public class NotLoggedIn extends TagSupport {

    private static final long serialVersionUID = 1L;

    boolean toBrowser = false;

    public void setToBrowser(String value) {
        if (value == null)
            toBrowser = false;
        else if (value.equalsIgnoreCase("true"))
            toBrowser = true;
        else
            toBrowser = false;
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

}


