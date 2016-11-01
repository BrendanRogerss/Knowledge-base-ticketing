package Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Brendan on 20/10/2016.
 */
public class Issue {

    private int issueID;
    private String user;
    private String content;
    private String status;
    private String category;
    private String title;
    private String description;
    private String resolutionDetails;
    private Date reportedDateTime;
    private Date resolvedDateTime;
    ArrayList<Comment> comments = new ArrayList<>();

    public void addComment(User user, Date dateTime, String content){

    }

    public void removeStaffComments(){

    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String getResolutionDetails() {
        return resolutionDetails;
    }

    public void setResolutionDetails(String resolutionDetails) {
        this.resolutionDetails = resolutionDetails;
    }

    public Date getReportedDateTime() {
        return reportedDateTime;
    }

    public void setReportedDateTime(Date reportedDateTime) {
        this.reportedDateTime = reportedDateTime;
    }

    public Date getResolvedDateTime() {
        return resolvedDateTime;
    }

    public void setResolvedDateTime(Date resolvedDateTime) {
        this.resolvedDateTime = resolvedDateTime;
    }

    public void setComments(ArrayList<Comment> comments){
        this.comments = comments;
    }

}
