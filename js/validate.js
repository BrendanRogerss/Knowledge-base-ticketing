function validate() {
    var reURL = /^(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/;
    //Retrieved from: http://stackoverflow.com/questions/1303872/trying-to-validate-url-using-javascript
    if (document.adForm.channelCategory.value == "select"){ //If a category is not selected.
        alert("Please select a category.");
        return false;
    }
    if (document.adForm.channelName.value == ""){ //If a channel name is not input.
        alert("Please enter a channel name.");
        return false;
    }
    if (document.adForm.channelURL.value == ""){ //If a channel URL is not input.
        alert("Please enter a URL.");
        return false;
    }
    if (reURL.test(document.adForm.channelURL.value) == false){  //If the URL is not valid.
        alert("Please enter a valid URL.");
        return false;
    }
    if (!document.adForm.restrictedMode[0].checked &&
        !document.adForm.restrictedMode[1].checked){ //If an option is not selected.
        alert("Please select an option for restricted mode.");
        return false;
    }
    if (document.adForm.email.value == ""){ //If an email is not input.
        alert("Please enter an email.");
        return false;
    }
    if (reEmail.test(document.adForm.email.value) == false){ //If the email is not valid.
        alert("Please enter a valid email.");
        return false;
    }
    else{ //Otherwise, submit.
        return true;
    }
}

function validateLogin(){

}