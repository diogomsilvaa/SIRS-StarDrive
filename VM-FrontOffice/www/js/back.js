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

    async function parBis(){
        var token = "aaa"
        var pass = prompt("Password");
        var password = "admin";

        // data = {username: login, password: pass}
        // const response = await fetch(/*url*/,{
        //     method: 'GET',
        //     mode: 'cors', // no-cors, *cors, same-origin
        //     cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        //     credentials: 'same-origin', // include, *same-origin, omit
        //     headers: {
        //         'Content-Type': 'application/json'
        //         // 'Content-Type': 'application/x-www-form-urlencoded',
        //     },
        //     redirect: 'follow', // manual, *follow, error
        //     referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        //     body: JSON.stringify(data) // body data type must match "Content-Type" header
        // });
        
        // if(response!=="err"){
        
        //     window.location.href = "./private.html" + "?User=" + login + "&?token="+response.json()['token']; // meter aqui o token
        // }
            
        if(login === reponse && pass === password){
            //login part
            window.location.href = "./private.html" + "?User=" + login + "&?token="+token // meter aqui o token
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
    var user = document.getElementById("userName");
    var text = document.createTextNode("Hello, " + username);

    user.appendChild(text);
}

function salaryStatus(){
    //get with token
    
    var salary = document.getElementById("salary");
    var text = document.createTextNode("Salary: " + "10000$");
    salary.appendChild(text);
}


let abs = [
    { Day: "01-01-2012", Reason: "Coding"},
    { Day: "01-01-2012", Reason: "Read"},
    { Day: "01-01-2012", Reason: "ghh"},
    { Day: "01-01-2012", Reason: "aasdas"},
    { Day: "01-01-2012", Reason: "asdsad"}
];

function absentLeaves(){
    tableCreate("absentLeaves", abs)
}

function refreshTable(){
    setInterval('tableCreate("tableSpot",inf)', 500);
    setInterval('machinesData()', 500);
}

function logout(){
    window.location.href='./index.html';
}

function machinesData(){
    let abs2 = [ 
        { Day: "01-01-2012", Reason: "Coding"},
        { Day: "01-01-2012", Reason: "Read"},
        { Day: "01-01-2012", Reason: "ghh"},
        { Day: "01-01-2012", Reason: "aasdas"},
        { Day: "01-01-2012", Reason: "asdsad"}
    ];

    let abs3 = [
        { name: "Monte Falco", job: "Coding", time: "01:00-02:00", c: Math.floor(Math.random() * 100) },
        { name: "Monte Falterona", job: "Read", time: "01:00-02:00",  c: Math.floor(Math.random() * 100) },
        { name: "Poggio Scali", job: "ghh", time: "01:00-02:00", c: Math.floor(Math.random() * 100)},
        { name: "Pratomagno", job: "aasdas", time: "01:00-02:00",  c: Math.floor(Math.random() * 100) },
        { name: "Monte Amiata", job: "asdsad", time: "01:00-02:00",  c: Math.floor(Math.random() * 100 ) }
    ];
    
    console.log([abs2,abs3])
    tableMachines("machineTable",[abs2,abs3]);
}