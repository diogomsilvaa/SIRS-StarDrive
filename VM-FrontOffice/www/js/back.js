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

    parBis();

    async function parBis(){
        var token = ""
        var pass = prompt("Password");

        data = {id: login, pass: pass}

        fetch("http://localhost:8081/auth",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data) // body data type must match "Content-Type" header
        }).then((response) => {
            // http status
            if(!response.ok){
                window.alert("Error");
                window.location.href = "./index.html";
                return;
            }

            response.json().then((data) => {
                token = data['content']
                window.location.href = "./private.html" + "?token=" + token // meter aqui o token
                
            })

        })   

        console.log(token)
        // fetch("https://192.168.0.1:8080/loginFront",{
        //     method: 'GET',
        //     headers: {
        //         'Content-Type': 'application/json'
        //         // 'Content-Type': 'application/x-www-form-urlencoded',
        //     },
        //     body: JSON.stringify({ token: token, id: login}) // body data type must match "Content-Type" header
        // }).then((response) => {
        //     // http status
        //     if(!response.ok)
        //         window.alert("Error");
        //         window.location.href = "./index.html";
        //         return;
        // });



    }  
}

function loadPrivateArea(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var token = urlParams.get("token")

    fetch("https://localhost:8080/loginFront",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify({ token: token}) // body data type must match "Content-Type" header
        }).then((response) => {
            // http status
            if(!response.ok)
                window.alert("Error");
                window.location.href = "./index.html";
                return;
        }).then((data) => {
            user = data
        }); 
    var user = document.getElementById("userName");
    var text = document.createTextNode("Hello, " + user["name"]);
    user.appendChild(text);
    

    var salary = document.getElementById("salary");
    var text = document.createTextNode(user["salary"]);
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
    

    fetch("http://localhost:8080/production/createLine",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            }
        }).then((response) => {
            // http status
            if(!response.ok)
                window.alert("Error");
                //window.location.href = "./index.html";
                return;
        }).then((data) => {
            console.log(data)
        });     
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
    
    //console.log([abs2,abs3])
    tableMachines("machineTable",[abs2,abs3]);
}

