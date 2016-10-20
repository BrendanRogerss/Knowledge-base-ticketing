package Config;

/**
 * Created by Brendan on 20/10/2016.
 */



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * MORE SHIT FROM UONSALES
 *
 * Classes that implement ServletContextListener are called when the servlet container (Tomcat) loads your application
 * This is useful to initialise objects that need to be used throughout your application
 * Created by Ross on 9/1/2015.
 */
@WebListener
public class SetupApplicationServletContextListener implements ServletContextListener {

    /**
     * This method gets called when the Servlet Container (Tomcat) loads your application
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // we are creating a new instance of the ShopModel class and storing it in the servlet context
        // the servlet context is accessible from all servlets (and JSPs) regardless of sessions
        //ShopModel shop = new ShopModel();
        servletContextEvent.getServletContext().setAttribute("shop", shop);
    }

    /**
     * This method gets called when the Servlet Container (Tomcat) closes your application
     * This can be done for a variety of reasons (server shutdown, need to clear memory, etc.)
     * This method is useful to persis (save) any variables or objects (like the ShopModel class created above)
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

