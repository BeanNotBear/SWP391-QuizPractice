var isValidate = false;

function validateFirstName (fName) {
    let firstName = document.querySelector("#firstName");
    if(fName.value === "") {
        isValidate = false;
        firstName.innerHTML = "you must fill first name";
    } else {
        isValidate = true;
        firstName.innerHTML = "";
    }
}

function validateLastName(lName) {
    let lastName = document.querySelector("#lastName");
    if(lName.value === "") {
        isValidate = false;
        lastName.innerHTML = "you must fill last name";
    } else {
        isValidate = true;
        lastName.innerHTML = "";
    }
}

function validateEmail(email) {
    let uEmail = document.querySelector("#uEmail");
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if(!emailPattern.test(email.value)) {
        isValidate = false;
        uEmail.innerHTML = "Email is wrong format";
    } else {
        isValidate = true;
        uEmail.innerHTML = "";
    }
}

function validatePhoneNumber(phone) {
    let phoneNumber = document.querySelector("#phoneNumber");
    const phonePattern = /^(\+?\d{1,4}?[-.\s]?)?(\(?\d{3}\)?[-.\s]?)?\d{3}[-.\s]?\d{4}$/;
    if(!phonePattern.test(phone.value)) {
        isValidate = false;
        phoneNumber.innerHTML = "Phone is wrong formats";
    } else {
        isValidate = true;
        phoneNumber.innerHTML = "";
    }
}

function validateDob(dob) {
    const dobDate = new Date(dob.value);
    
    // Get the current date
    const currentDate = new Date();
    
    // Calculate the age
    let age = currentDate.getFullYear() - dobDate.getFullYear();
    
    // Get msg of dob
    let dobMsg = document.querySelector("#dobMsg");
    
    if(!(age >= 16)) {
        isValidate = false;
        dobMsg.innerHTML = "Your age needs to be greater than 16";
    } else {
        isValidate = true;
        dobMsg.innerHTML = "";
    }
}

function validateUsername(username) {
    let usernameMsg = document.querySelector("#usernameMsg");
    if(username.value === "") {
        isValidate = false;
        usernameMsg.innerHTML = "you must fill username";
    } else {
        isValidate = true;
        usernameMsg.innerHTML = "";
    }
}

function validatePassword(password) {
    let passwordMsg = document.querySelector("#passwordMsg");
    const passwordPattern = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$/;
    if(!passwordPattern.test(password.value)) {
        isValidate = false;
        passwordMsg.innerHTML = "Password is wrong format";
    } else {
        isValidate = true;
        passwordMsg.innerHTML = "";
    }
}

function checkPasswordAndCfPassword(cfPassword) {
    let cfPasswordMsg = document.querySelector("#cfPassowrdMsg");
    let password = document.querySelector("#password");
    if(cfPassword.value !== password.value) {
        isValidate = false;
        cfPasswordMsg.innerHTML = "Password and Confirm password do not match";
    } else {
        isValidate = true;
        cfPasswordMsg.innerHTML = "";
    }
}

function isAllowSendData(submit) {
    if(!isValidate) {
        submit.disable = true;
    } else {
        submit.disable = false;
    }
}
