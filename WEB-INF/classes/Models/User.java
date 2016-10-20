package Models;

/**
 * Created by Brendan on 20/10/2016.
 */
public class User {

    private String username;
    private String password;
    private String type;
    private boolean loggedIn;
    //boolean staff
    //might be better to use the bool rather than the string


    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
