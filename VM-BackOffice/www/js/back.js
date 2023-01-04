// function clickMe() {
//     var header = document.getElementById("title");
//     header.innerHTML = "po crlh";
// }

// var button = document.getElementById("but");
// button.addEventListener("click", clickMe);

// fetch data from random api
function fetchRandom() {
    fetch("https://randomuser.me/api/")
        .then(response => response.json())
        .then(data => {
            var user = data.results[0];
            var name = user.name.first + " " + user.name.last;
            var email = user.email;
            var phone = user.phone;
            var image = user.picture.large;

            document.getElementById("name").innerHTML = name;
            document.getElementById("email").innerHTML = email;
            document.getElementById("phone").innerHTML = phone;
            document.getElementById("image").src = image;
        });
    }


    

let inf = [
    { name: "Monte Falco", job: "Coding", time: "01:00-02:00", c: Math.floor(Math.random() * 100) },
    { name: "Monte Falterona", job: "Read", time: "01:00-02:00",  c: Math.floor(Math.random() * 100) },
    { name: "Poggio Scali", job: "ghh", time: "01:00-02:00", c: Math.floor(Math.random() * 100)},
    { name: "Pratomagno", job: "aasdas", time: "01:00-02:00",  c: Math.floor(Math.random() * 100) },
    { name: "Monte Amiata", job: "asdsad", time: "01:00-02:00",  c: Math.floor(Math.random() * 100 ) }
];

function tableCreate(id, info) {
    const divShowData = document.getElementById(id);
    divShowData.innerHTML = "";
    divShowData.appendChild(tableGen(info));
    
}

function tableGen(data){
    let col = [];
    for (let i = 0; i < data.length; i++) {
        for (let key in data[i]) {
            if (col.indexOf(key) === -1) {
            col.push(key);
            }
        }
    }
    const table = document.createElement("table");

    let tr = table.insertRow(-1);                   // table row.

    for (let i = 0; i < col.length; i++) {
        let th = document.createElement("th");      // table header.
        th.innerHTML = col[i];
        tr.appendChild(th);
    }

    for (let i = 0; i < data.length; i++) {

        tr = table.insertRow(-1);

        for (let j = 0; j < col.length; j++) {
            let tabCell = tr.insertCell(-1);
            tabCell.innerHTML = data[i][col[j]];
        }
    }

    return table;
}
function tableMachines(id, datas){
    const divShowData = document.getElementById(id);
    divShowData.innerHTML = "";
    
    for(var i=0;i<datas.length;i++){
        divShowData.appendChild(tableGen(datas[i]));
    }
}

function login(){
         
    var login = prompt("Username");
    var reponse = "admin";

    parBis();

    function parBis(){
        var token = "aaa"
        var pass = prompt("Password");
        var password = "admin";
        if(login === reponse && pass === password){
            //login part
            window.location.href = "./private.html" + "?User=" + login + "&token="+token // meter aqui o token
        }
        else{
            window.alert("User or password incorrect");
        }
    }  
}

function loadPrivateArea(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var username = urlParams.get("User")
    console.log("ai")
    console.log("user",username)
    var user = document.getElementById("userName");
    var text = document.createTextNode("Hello, " + username);

    user.appendChild(text);
}


let abs = [
    { Day: "01-01-2012", Reason: "Coding"},
    { Day: "01-01-2012", Reason: "Read"},
    { Day: "01-01-2012", Reason: "ghh"},
    { Day: "01-01-2012", Reason: "aasdas"},
    { Day: "01-01-2012", Reason: "asdsad"}
];


function logout(){
    window.location.href='./index.html';
}

function loadEmployees(){
    html = "";
        obj = {
            "1" : "Name",
            "2": "Age",
            "3" : "Gender"
        }
        for(var key in obj) {
            html += "<option value=" + key  + ">" +obj[key] + "</option>"
        }
        document.getElementById("employeeDropTable").innerHTML = html;
}

function backPrivate(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var username = urlParams.get("User")
    var token = urlParams.get("token")
    window.location.href='./private.html' + "?User=" + username + "&token="+token;
}

function goCreateShift(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    


    var username = urlParams.get("User")
    var token = urlParams.get("token")
    window.location.href='./employeeShift.html'+ "?User=" + username + "&token="+token;
}

function goChangeEmployee(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var username = urlParams.get("User")
    var token = urlParams.get("token")
    window.location.href='./employeeChange.html'+ "?User=" + username + "&token="+token;
}

function createEmployeeShift(){
    console.log(document.getElementById("employeeDropTable").value)
    console.log(document.getElementById("fdata").value)
    console.log(document.getElementById("fduration").value)

    // send request
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var username = urlParams.get("User")
    var token = urlParams.get("token")
    window.location.href='./private.html' + "?User=" + username + "&token="+token;

}

function changeEmployee(){
    console.log(document.getElementById("fid").value)
    console.log(document.getElementById("fpass").value)
    console.log(document.getElementById("fpass").value)
    //send request

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var username = urlParams.get("User")
    var token = urlParams.get("token")
    window.location.href='./private.html' + "?User=" + username + "&token="+token;
}