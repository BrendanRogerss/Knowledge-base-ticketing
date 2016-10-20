package Models;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Brendan on 20/10/2016.
 *
 */
public class IssueList {

    private String listName;
    private ArrayList<Issue> issues = new ArrayList<Issue>();

    public Iterator<Issue> getIssueList(){ //might be best to return the iterator
        return issues.iterator();
    }

    public void addIssue(Issue issue){
        issues.add(issue);
    }

}
