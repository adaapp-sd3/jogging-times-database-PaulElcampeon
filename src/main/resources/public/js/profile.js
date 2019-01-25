var introDiv = document.getElementById("introDiv");
var avgSpeedDiv = document.getElementById("avgSpeedDiv");
var totalDistDiv = document.getElementById("totalDistDiv");
var totalTimeDiv = document.getElementById("totalTimeDiv");
var sectionBoyz = document.getElementById("sectionBoyz");
var deleteAccountBtn = document.getElementById("deleteAccountBtn");

var usersName;
var usersEmail;
var usersId;
var myJogs;
var avgSpeedAmount;


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
    getUsersData();
}


function getUsersData() {

    console.log("CONFIRMING CREDENTIALS");

    let url = 'http://localhost:8080/jogs/'+usersId;

    fetch(url, {
        method: 'GET',
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            console.log(data);

            if(data.totalDistance==null || data.totalTime==null) {

                totalDistDiv.innerHTML = 0;
                totalTimeDiv.innerHTML = 0;
                avgSpeedDiv.innerHTML = 0;

            } else {

                totalDistDiv.innerHTML = data.totalDistance;
                totalTimeDiv.innerHTML = data.totalTime;
                avgSpeedDiv.innerHTML = (data.totalDistance/data.totalTime).toFixed(2);
                myJogs = data.myJogs;

                if(myJogs.length>0){
                    myJogs.forEach(element=>{
                    addMyJogs(element)
                    });
                }
            }
        })
}

function addMyJogs(data){
    let tempATag1 = document.createElement("button");
    let tempH3Tag = document.createElement("h3");
    let tempPTag2 = document.createElement("p");
    let tempSpanTag1 = document.createElement("span");
    let tempSpanTag2 = document.createElement("span");
    let tempPTag3 = document.createElement("p");
    let tempSpanTag3 = document.createElement("span");
    tempATag1.classList.add('flex-auto', 'w-64', 'm-4', 'p-5', 'bg-white', 'shadow', 'rounded-lg', 'text-black', 'no-underline');
    tempH3Tag.classList.add('text-grey-darker', 'uppercase', 'text-base', 'font-normal');
    tempPTag2.classList.add('text-xl', 'mt-2');
    tempSpanTag1.classList.add('uppercase', 'text-sm', 'text-grey-darker', 'font-semibold', '-ml-1');
    tempSpanTag2.classList.add('uppercase', 'text-sm', 'text-grey-darker', 'font-semibold', '-ml-1');
    tempPTag3.classList.add('text-xl', 'mt-1');
    tempSpanTag3.classList.add('uppercase', 'text-sm', 'text-grey-darker', 'font-semibold', '-ml-1')


    tempH3Tag.innerHTML = data.date;


    tempPTag2.innerHTML = data.totalDistance;
    tempSpanTag1.innerHTML = " km";
    tempPTag2.appendChild(tempSpanTag1);
    tempPTag2.innerHTML = tempPTag2.innerHTML + " in " + data.totalTime;
    tempSpanTag2.innerHTML = " hrs";
    tempPTag2.appendChild(tempSpanTag2);


    tempPTag3.innerHTML = "Avg. speed: " + (data.totalDistance/data.totalTime).toFixed(2);
    tempSpanTag3.innerHTML = " km/hr";
    tempPTag3.appendChild(tempSpanTag3);


    tempATag1.appendChild(tempH3Tag);
    tempATag1.appendChild(tempPTag2);
    tempATag1.appendChild(tempPTag3);

//    tempATag1.setAttribute("href", "http://localhost:8080/times/{{ id }}");
    tempATag1.addEventListener("click", (event)=>{
        sessionStorage.setItem("myJog", JSON.stringify(data));
        location.href="http://localhost:8080/edit-time";
    })
    sectionBoyz.appendChild(tempATag1);
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

