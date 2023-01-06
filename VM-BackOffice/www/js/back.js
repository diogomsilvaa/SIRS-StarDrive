
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
                var text = document.createTextNode("Hello Engineer, " + data["name"]);
                user.appendChild(text);
            })
    }); 

    fetch("http://localhost:8080/user/getEmployees",{
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
            var table = document.getElementById("tableSpot");
            table.appendChild(tableGen(data))
        })   
    });

    fetch("http://localhost:8080/shift/getShifts",{
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
            var table = document.getElementById("shifts");
            table.appendChild(tableGen(data))
        })   
    });
}


function logout(){
    document.cookie = "token=; path=/"
    window.location.href='./index.html';
}

function loadEmployees(){
    data = {token: getCookie("token")}

    fetch("http://localhost:8080/user/getEmployees",{
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
            let json = data;
            for(var x in json){
                delete (json[x]["salary"])
                delete (json[x]["creationDate"])
                delete (json[x]["role"])
                delete (json[x]["shiftsIDs"])
                delete (json[x]["absentDays"])
            }
            html = "";
            for(var key in json) {
                html += "<option value=" +json[key]["id"]  + ">" + json[key]["id"] + " - " + json[key]["name"] + "</option>"
            }
            
            document.getElementById("employeeDropTable").innerHTML = html;
            //table.appendChild(tableGen(data))

        })
    });
}

function loadShifts(){
    data = {token: getCookie("token")}

    fetch("http://localhost:8080/shift/getShifts",{
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
            let json = data;
            for(var x in json){
                delete (json[x]["employeesIDs"])
                delete (json[x]["starTime"])
                delete (json[x]["endTime"])
            }
            html = "";
            for(var key in json) {
                html += "<option value=" +json[key]["id"]  + ">" + json[key]["id"] + "</option>"
            }
            document.getElementById("shiftDropTable").innerHTML = html;

        })
    });
}

function shiftPage(){
    loadEmployees()
    loadShifts()
}

function goChangeEmployee(){
    window.location.href='./employeeChange.html'
}
function goCreateShift(){
    window.location.href='./employeeShift.html'
}
function goAssemblyLines(){
    window.location.href='./assemblyLines.html'
}

function employeeChange(){
    data = {token : getCookie("token"), id : document.getElementById("employeeDropTable").value, salary : document.getElementById("fsalary").value}

    fetch("http://localhost:8080/user/changeSalary",{
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
            //logout();
            return;
        }

    });    
    window.location.href='./private.html' 
}


function addShift(){
    data = {token : getCookie("token"), employeeId : document.getElementById("employeeDropTable").value,  shiftId : document.getElementById("shiftDropTable").value}
    console.log(data)
    fetch("http://localhost:8080/shift/addEmployee",{
    method: 'PUT',
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

    });
    window.location.href='./private.html' 
}


function createShift(){

    var date = document.getElementById("fdata").value.replace("-","/").replace("-","/") + " " + document.getElementById("fStartTime").value
    console.log(date)
    data = {token : getCookie("token"), start : date, duration : document.getElementById("fduration").value}
    console.log(data)
    fetch("http://localhost:8080/shift/create",{
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

    });
    window.location.href='./private.html' 
}




function backPrivate(){
    window.location.href='./private.html' 
}