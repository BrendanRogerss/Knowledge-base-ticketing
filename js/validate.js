function validateLogin(){
    if (document.getElementById('user').value == ""){ //If a username is not input.
        alert("Please enter a username.");
        return false;
    }
    else if (document.getElementById('password').value == ""){ //If a password is not input.
        alert("Please enter a password.");
        return false;
    }
    else{
        return true;
    }
}
//We need login, Add comment, add solution, report issue


function validateReportIssue(){
    if (document.getElementById('category').value == "SelectACategory"){ //If a category is not selected.
        alert("Please select a category.");
        return false;
    }
    else if (document.getElementById('title').value == ""){ //If a title is not input.
        alert("Please enter a title.");
        return false;
    }
    else if (!document.getElementById('yes-internal').checked &&
        !document.getElementById('no-internal').checked){ //If an option is not selected.
        alert("Please select an option for internal websites.");
        return false;
    }
    else if (!document.getElementById('yes-alternate').checked &&
        !document.getElementById('no-alternate').checked){ //If an option is not selected.
        alert("Please select an option for alternate browsers.");
        return false;
    }
    else if (!document.getElementById('yes-restart').checked &&
        !document.getElementById('no-restart').checked){ //If an option is not selected.
        alert("Please select an option for restarting computer.");
        return false;
    }
    else{
        return true;
    }
}