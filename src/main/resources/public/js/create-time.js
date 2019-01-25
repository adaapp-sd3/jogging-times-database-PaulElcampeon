var introDiv = document.getElementById("introDiv");
var timeInput = document.getElementById("timeInput");
var distanceInput = document.getElementById("distanceInput");
var dateInput = document.getElementById("dateInput");
var submitBtn = document.getElementById("submitBtn");
var deleteAccountBtn = document.getElementById("deleteAccountBtn");

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

    usersName = JSON.parse(sessionStorage.getItem("name"));
    usersEmail = JSON.parse(sessionStorage.getItem("email"));
    usersId = JSON.parse(sessionStorage.getItem("usersId"));

    introDiv.innerHTML = "Signed in as " + usersName + " " + usersEmail + introDiv.innerHTML;
}

submitBtn.addEventListener("click",(event)=>{
    console.log("someone clicked the submit button")
    event.preventDefault();
    if(dateInput.value!=null || timeInput.value!=null||distanceInput.value!=null ||usersId!=null){
        let addJogRequest = {date:dateInput.value, totalTime:timeInput.value, totalDistance:distanceInput.value, userId:usersId}
        console.log(addJogRequest);
        addNewTime(addJogRequest)
    }else {
        showErrorMessage("Blank Fields")

    }
})


function addNewTime(data) {

    console.log("ADDING TIME");

    let url = 'http://localhost:8080/add-jog';

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
        //this is a perfect world no problems will happen too lazy to code for possible errors
            location.href = "http://localhost:8080/profile";
        })

}


function showErrorMessage(message){
    errorMessageDiv.innerHTML = "Error: " + message;
    errorMessageDiv.style.display = "block";
}


function deleteAccount(data){

 let url = 'http://localhost:8080/delete-user';

    fetch(url, {
        method: 'DELETE',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            sessionStorage.removeItem("usersId");
            sessionStorage.removeItem("name");
            sessionStorage.removeItem("email");
            location.href = "http://localhost:8080/delete-successful";
        })
}

deleteAccountBtn.addEventListener("click",(event)=>{
    let deleteAccountObject = {userId:usersId};
    deleteAccount(deleteAccountObject);
})

