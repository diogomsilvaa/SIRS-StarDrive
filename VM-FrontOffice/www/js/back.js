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
                
                fetch("http://localhost:8080/user/loginFront",{
                    method: 'Post',
                    headers: {
                        'Content-Type': 'application/json'
                        // 'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: JSON.stringify({ token: token, id: login}) // body data type must match "Content-Type" header
                }).then((response) => {
                    // http status
                    if(!response.ok){
                        window.alert("Error");
                        window.location.href = "./index.html";
                        return;
                    }
                        
                    window.location.href = "./private.html"  
                    document.cookie = "token="+token+"; path=/"
                    
                });
            })

        })   

        



    }  
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function loadPrivateArea(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    //var token = urlParams.get("token")

    console.log(  getCookie("token"))
    data = {token: getCookie("token")}
    console.log(data)
    fetch("http://localhost:8080/user/getUser",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
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
                console.log(data)

                var user = document.getElementById("userName");
                var text = document.createTextNode("Hello, " + data["name"]);
                user.appendChild(text);
                

                var salary = document.getElementById("salary");
                var text = document.createTextNode(data["salary"] + "$ /month");
                salary.appendChild(text);

                var job = document.getElementById("job");
                var text = document.createTextNode(data["role"]);
                job.appendChild(text);
            })

            
        }); 

    
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
    

    // fetch("http://localhost:8080/production/createLine",{
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //             // 'Content-Type': 'application/x-www-form-urlencoded',
    //         }
    //     }).then((response) => {
    //         // http status
    //         if(!response.ok)
    //             window.alert("Error");
    //             //window.location.href = "./index.html";
    //             return;
    //     }).then((data) => {
    //         console.log(data)
    //     });     
    setInterval('tableCreate("tableSpot",inf)', 500);
    setInterval('machinesData()', 500);
}

function logout(){
    document.cookie = "token=; path=/"
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

