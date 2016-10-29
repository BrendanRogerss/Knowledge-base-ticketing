package Models;

/**
 * Created by Brendan on 20/10/2016.
 */
public class User {

    private String username;
    private String password;
    private String type;
    private boolean loggedIn;
    private boolean staff;
    //might be better to use the bool rather than the string

    User(){

    }

    public boolean isStaff(){
        return staff;
    }

    public void setStaff(boolean staff){
        this.staff = staff;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
