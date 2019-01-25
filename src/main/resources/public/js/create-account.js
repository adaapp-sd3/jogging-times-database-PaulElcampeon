var errorMessageDiv = document.getElementById("errorMessageDiv");
var nameInput = document.getElementById("nameInput");
var emailInput = document.getElementById("emailInput");
var passwordInput = document.getElementById("passwordInput");
var confirmPasswordInput = document.getElementById("confirmPasswordInput");
var submitBtn = document.getElementById("submitBtn");

var usersId;

errorMessageDiv.style.display = "none";

if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);
}

function ready () {


}


submitBtn.addEventListener("click",(event)=>{
    console.log("somone clicked the submit button")
    event.preventDefault();
    let createUserDtoRequest = {name:nameInput.value, email:emailInput.value, password:passwordInput.value, confirmPassword:confirmPasswordInput.value}
    createUser(createUserDtoRequest)
})

function createUser(data) {

    console.log("CREATING ACCOUNT");

    let url = 'http://localhost:8080/create-user';

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
                usersId = data.userId;
                saveDataToLocalStorage();
                location.href = "http://localhost:8080/profile";
            } else {

                showErrorMessage(data.error);
                clearInputs();
            }
            console.log(data);
        })

}

function showErrorMessage(message){
    errorMessageDiv.innerHTML = "Error: " + message;
    errorMessageDiv.style.display = "block";
}

function clearInputs(){
    emailInput.value = "";
    passwordInput.value = "";
    confirmPasswordInput.value = "";
    nameInput.value = "";
}

function saveDataToLocalStorage(){
    sessionStorage.setItem("name", JSON.stringify(nameInput.value));
    sessionStorage.setItem("email", JSON.stringify(emailInput.value));
    sessionStorage.setItem("usersId", JSON.stringify(usersId));
    passwordInput.value = "";
    emailInput.value = "";
    nameInput.value = "";
    confirmPasswordInput.value = "";
}