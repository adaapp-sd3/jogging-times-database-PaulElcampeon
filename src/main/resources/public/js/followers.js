var introDiv = document.getElementById("introDiv");
var usersImNotFollowingDiv = document.getElementById("usersImNotFollowingDiv");
var imFollowing = document.getElementById("imFollowing");
var followingMe = document.getElementById("followingMe");
var deleteAccountBtn = document.getElementById("deleteAccountBtn");

var usersName;
var usersEmail;
var usersId;


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
    getAllUsersExceptMe();
}


function UsersImNotFollowingLabel(data, divToAppend){

    let tempATag1 = document.createElement("div");
    let tempH3Tag = document.createElement("h3");
    let tempFollowButton = document.createElement("button");

    tempATag1.classList.add('flex-auto', 'text-center', 'w-64', 'm-4', 'p-5', 'bg-white', 'shadow', 'rounded-lg', 'text-black', 'no-underline');
    tempH3Tag.classList.add('text-grey-darker', 'uppercase', 'text-base', 'font-normal');
    tempFollowButton.classList.add('bg-purple', 'hover:bg-purple-dark', 'text-white', 'font-bold', 'py-2', 'px-4', 'rounded-full', 'mt-2');

    tempFollowButton.innerHTML = "Follow";
    tempH3Tag.innerHTML = "Name: "+data.name;

    tempATag1.appendChild(tempH3Tag);
    tempATag1.appendChild(tempFollowButton);

    let followRequestDto = {followersId:usersId , followedId:data.id}

    tempFollowButton.addEventListener("click", (event)=>{
        followUser(followRequestDto);
    })

    divToAppend.appendChild(tempATag1);
}

function UsersImFollowingLabel(data, divToAppend){

    let tempATag1 = document.createElement("div");
    let tempH3Tag = document.createElement("h3");
    let tempUnfollowButton = document.createElement("button");

    tempATag1.classList.add('flex-auto', 'text-center', 'w-64', 'm-4', 'p-5', 'bg-white', 'shadow', 'rounded-lg', 'text-black', 'no-underline');
    tempH3Tag.classList.add('text-grey-darker', 'uppercase', 'text-base', 'font-normal');
    tempUnfollowButton.classList.add('bg-purple', 'hover:bg-purple-dark', 'text-white', 'font-bold', 'py-2', 'px-4', 'rounded-full', 'mt-2');

    tempUnfollowButton.innerHTML = "Unfollow"
    tempH3Tag.innerHTML = "Name: "+data.name;

    tempATag1.appendChild(tempH3Tag);
    tempATag1.appendChild(tempUnfollowButton);

    let followRequestDto = {followersId:usersId , followedId:data.id}

    tempUnfollowButton.addEventListener("click", (event)=>{
            unfollowUser(followRequestDto);
    })

    divToAppend.appendChild(tempATag1);
}


function UsersFollowingMeLabel(data, divToAppend){

    let tempATag1 = document.createElement("div");
    let tempH3Tag = document.createElement("h3");

    tempATag1.classList.add('flex-auto', 'text-center', 'w-64', 'm-4', 'p-5', 'bg-white', 'shadow', 'rounded-lg', 'text-black', 'no-underline');
    tempH3Tag.classList.add('text-grey-darker', 'uppercase', 'text-base', 'font-normal');

    tempH3Tag.innerHTML = "Name: "+data.name;

    tempATag1.appendChild(tempH3Tag);

    divToAppend.appendChild(tempATag1);
}

function followUser(data){

let url = 'http://localhost:8080/add-follower';

    fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then((data) => {
            getAllUsersExceptMe();
        })
}

function unfollowUser(data){

let url = 'http://localhost:8080/remove-follower';

    fetch(url, {
        method: 'DELETE',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then((data) => {
            getAllUsersExceptMe();
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



deleteAccountBtn.addEventListener("click",(event)=>{
    let deleteAccountObject = {userId:usersId};
    deleteAccount(deleteAccountObject);
})

function getAllUsersExceptMe(){
let url = 'http://localhost:8080/user-all/'+usersId;

    fetch(url, {
        method: 'GET',
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {

            clearDivs();

            console.log(data);
            data.usersImNotFollowing.forEach(element=>{
                UsersImNotFollowingLabel(element, usersImNotFollowingDiv)
            })
            data.peopleImFollowing.forEach(element=>{
                UsersImFollowingLabel(element, imFollowing)
            })
            data.peopleFollowingMe.forEach(element=>{
                UsersFollowingMeLabel(element, followingMe)
            })
        })
}

function clearDivs(){
    usersImNotFollowingDiv.innerHTML = "";
    imFollowing.innerHTML = "";
    followingMe.innerHTML = "";
}