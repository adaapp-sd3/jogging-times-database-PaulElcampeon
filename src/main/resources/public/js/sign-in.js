var errorMessageDiv = document.getElementById("errorMessageDiv");
var emailInput = document.getElementById("emailInput");
var passwordInput = document.getElementById("passwordInput");
var submitBtn = document.getElementById("submitBtn");
var usersName;
var usersEmail;
var usersId;

errorMessageDiv.style.display = "none";

if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);
}

function ready () {

    checkIfUsersDetailsIsCached();

}

submitBtn.addEventListener("click",(event)=>{
    console.log("somone clicked the submit button")
    event.preventDefault();
    let signInObject = {email:emailInput.value, password:passwordInput.value}
    confirmUserCredentials(signInObject)
})

function confirmUserCredentials(data) {

    console.log("CONFIRMING CREDENTIALS");

    let url = 'http://localhost:8080/sign-in';

    fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            if(data.success){
                usersName = data.message;
                usersId = data.optional;
                console.log(usersId)
                saveDataToLocalStorage();
                location.href = "http://localhost:8080/profile";
            } else {
                showErrorMessage(data.message)
                clearInputs();
            }
        })

}

function showErrorMessage(message){
    errorMessageDiv.innerHTML = "Error: " + message;
    errorMessageDiv.style.display = "block";
}

function clearInputs(){
    emailInput.value = "";
    passwordInput.value = "";
}

function checkIfUsersDetailsIsCached() {

    usersName = JSON.parse(sessionStorage.getItem("name"));
    usersEmail = JSON.parse(sessionStorage.getItem("email"));
    usersId = JSON.parse(sessionStorage.getItem("usersId"));

    if(usersName!=null && usersEmail!=null) {
        location.href = "http://localhost:8080/profile";
    }
}

function saveDataToLocalStorage(){
    sessionStorage.setItem("name", JSON.stringify(usersName));
    sessionStorage.setItem("email", JSON.stringify(emailInput.value));
    sessionStorage.setItem("usersId", JSON.stringify(usersId));

    passwordInput.value = "";
    emailInput.value = "";
}