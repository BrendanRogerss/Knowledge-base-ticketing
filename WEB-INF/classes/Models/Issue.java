package Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Brendan on 20/10/2016.
 */
public class Issue {

    private int issueID;
    private String content;
    private String state;
    private String Category;
    private String Title;
    private String Description;
    private String resolutionDetails;
    private Date reportedDateTime;
    private Date resolvedDateTime;
    ArrayList<Comment> comments = new ArrayList<>();

    public void addComment(User user, Date dateTime, String content){

    }

    public void removeStaffComments(){

    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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
}
