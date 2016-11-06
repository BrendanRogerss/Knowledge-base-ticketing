package Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Brendan on 20/10/2016.
 */
public class Issue {

    private int issueID;
    private String state;
    private String category;
    private String title;
    private String description;
    private String location;
    private String browser;
    private String website;
    private boolean internalAccess;
    private boolean alternateBrowser;
    private boolean computerRestart;
    private String errorMessage;
    private String resolutionDetails;
    private String reportedDateTime;
    private String resolvedDateTime;
    private String username;
    private boolean hasNotification;
    ArrayList<Comment> comments = new ArrayList<>();
    

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isInternalAccess() {
        return internalAccess;
    }

    public void setInternalAccess(boolean internalAccess) {
        this.internalAccess = internalAccess;
    }

    public boolean isAlternateBrowser() {
        return alternateBrowser;
    }

    public void setAlternateBrowser(boolean alternateBrowser) {
        this.alternateBrowser = alternateBrowser;
    }

    public boolean isComputerRestart() {
        return computerRestart;
    }

    public void setComputerRestart(boolean computerRestart) {
        this.computerRestart = computerRestart;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResolutionDetails() {
        return resolutionDetails;
    }

    public void setResolutionDetails(String resolutionDetails) {
        this.resolutionDetails = resolutionDetails;
    }

    public String getReportedDateTime() {
        return reportedDateTime;
    }

    public void setReportedDateTime(String reportedDateTime) {
        this.reportedDateTime = reportedDateTime;
    }

    public String getResolvedDateTime() {
        return resolvedDateTime;
    }

    public void setResolvedDateTime(String resolvedDateTime) {
        this.resolvedDateTime = resolvedDateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean hasNotification() {
        return hasNotification;
    }

    public void setNotification(boolean hasNotification) {
        this.hasNotification = hasNotification;
    }
}
