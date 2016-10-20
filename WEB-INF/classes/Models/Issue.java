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
    private String category;
    private String title;
    private String description;
    private String resolutionDetails;
    private Date reportedDateTime;
    private Date resolvedDateTime;
    ArrayList<Comment> comments = new ArrayList<>();

    public Issue(int i){
        //set default values till we have the sql database running
        issueID = i;
        content = Integer.toString(i);
        state = Integer.toString(i);
        title  = Integer.toString(i);
        description = Integer.toString(i);
        resolutionDetails = Integer.toString(i);
        reportedDateTime = new Date(i);
        resolvedDateTime = new Date(i);
    }

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
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
