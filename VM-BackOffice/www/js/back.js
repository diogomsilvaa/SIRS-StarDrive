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
                
                fetch("http://localhost:8080/user/loginBack",{
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


let employees = [
    { Name: "Carlos", Salary: "1231231"},
    { Name: "Diogo", Salary: "1231231"},
    { Name: "Miguel", Salary: "1231231"}
];

function loadPrivateArea(){
    data = {token: getCookie("token")}

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
                logout();
                return;
            }
            response.json().then((data) => {
                console.log(data)

                var user = document.getElementById("userName");
                var text = document.createTextNode("Hell Engineer, " + data["name"]);
                user.appendChild(text);
            })

            
        }); 

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
            logout();
            return;
        }
        response.json().then((data) => {
            console.log(data)

            var user = document.getElementById("userName");
            var text = document.createTextNode("Hell Engineer, " + data["name"]);
            user.appendChild(text);
        })

        
    });

   

    var table = document.getElementById("tableSpot");
    table.appendChild(tableGen(employees))

}


function logout(){
    document.cookie = "token=; path=/"
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