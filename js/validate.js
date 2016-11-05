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

function validateReportIssue(){

}