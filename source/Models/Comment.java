package Models;

import java.util.Date;

/**
 * Created by Brendan on 20/10/2016.
 */
public class Comment {

    private Date dateTime;
    private String content;
    private User user;

    public Comment(){

    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
