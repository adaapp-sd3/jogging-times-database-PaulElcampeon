var introDiv = document.getElementById("introDiv");
var distanceInput = document.getElementById("distanceInput");
var dateInput = document.getElementById("dateInput");
var timeInput = document.getElementById("timeInput");
var submitBtn = document.getElementById("submitBtn");
var deleteBtn = document.getElementById("deleteBtn");
var deleteAccountBtn = document.getElementById("deleteAccountBtn");

var usersName;
var usersEmail;
var usersId;
var myJog;

var deleteJogDtoRequest;

if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);
}

function ready () {

    usersName = JSON.parse(sessionStorage.getItem("name"));
    usersEmail = JSON.parse(sessionStorage.getItem("email"));
    usersId = JSON.parse(sessionStorage.getItem("usersId"));
    myJog = JSON.parse(sessionStorage.getItem("myJog"));
    deleteJogDtoRequest = {jogId:myJog.id};
    populateJog(myJog);

    introDiv.innerHTML = "Signed in as " + usersName + " " + usersEmail + introDiv.innerHTML;

}

function populateJog(data){
    dateInput.value = myJog.date;
    distanceInput.value = myJog.totalDistance;
    timeInput.value = myJog.totalTime;
}

submitBtn.addEventListener("click",(event)=>{
    event.preventDefault();
    let updateJogRequest = {id:myJog.id, date:dateInput.value, totalTime:timeInput.value, totalDistance:distanceInput.value, userId:usersId}
    updateJog(updateJogRequest);
});

deleteBtn.addEventListener("click",(event)=>{
    deleteJog(deleteJogDtoRequest);
});


function deleteJog(data){

    console.log("CONFIRMING CREDENTIALS");

    let url = 'http://localhost:8080/delete-jog';

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
            location.href = "http://localhost:8080/profile";
        })
}


function updateJog(data){

    console.log("CONFIRMING CREDENTIALS");

    let url = 'http://localhost:8080/update-jog';

    fetch(url, {
        method: 'PUT',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            location.href = "http://localhost:8080/profile";
        })
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

