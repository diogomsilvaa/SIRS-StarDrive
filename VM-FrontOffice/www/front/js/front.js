address = "https://192.168.0.1:8080"
auth = "https://10.0.3.200:8081"

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

function login(){
         
    var login = prompt("Username");

    parBis();

    async function parBis(){
        var token = ""
        var pass = prompt("Password");

        data = {id: login, pass: pass}

        fetch(auth + "/auth",{
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
                
                fetch(address + "/user/loginFront",{
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

    data = {token: getCookie("token")}

    fetch(address + "/user/getUser",{
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
                var text = document.createTextNode("Hello, " + data["name"]);
                user.appendChild(text);
                

                var salary = document.getElementById("salary");
                var text = document.createTextNode(data["salary"] + "$ /month");
                salary.appendChild(text);

                var job = document.getElementById("job");
                var text = document.createTextNode(data["role"]);
                job.appendChild(text);


                if(data["absentDays"].length > 0){
                    var absentLeaves = document.getElementById("absentLeaves");
                    console.log(data["absentDays"])

                    for(var key in data["absentDays"]){
                        console.log(data["absentDays"][key]);
                        var p = document.getElementById("absentLeaves");
                        p.appendChild(document.createTextNode(data["absentDays"][key]));
                        p.appendChild(document.createElement("br"));
                    }
                }
                else{
                    var absentLeaves = document.getElementById("absentLeaves");
                    var text = document.createTextNode("No absent days!")
                    absentLeaves.appendChild(text);
                }
                
                if(data["shiftsIDs"].length > 0){
                    var shifts = document.getElementById("shifts");
                    shifts.appendChild(tableGen(data["shiftsIDs"]));
                }
                else{
                    var shifts = document.getElementById("shifts");
                    var text = document.createTextNode("No shifts!")
                    shifts.appendChild(text);
                }
                
            })

            
        }); 

    
}


function refreshTable(){
    

    fetch(address + "/shift/get",{
            method: 'GET',
        }).then((response) => {
            // http status
            if(!response.ok){
                window.alert("Error");
                return;
            }
                
            response.json().then((data) => {

                tableCreate("tableSpot",data)
            })
        });     
    
    setInterval('asseblyLinesTable()', 500);
}

function logout(){
    document.cookie = "token=; path=/"
    window.location.href='./index.html';
}


function asseblyLinesTable(){
    
    fetch(address + "/production/getLines",{
            method: 'GET',
        }).then((response) => {
            // http status
            if(!response.ok){
                window.alert("Error");
                return;
            }
                
            response.json().then((data) => {
                const divShowData = document.getElementById("asseblyLinesTable");
                divShowData.innerHTML = "";

                divShowData.appendChild(tableGen(data));

                console.log(data);

                const spot = document.getElementById("lines");
                spot.innerHTML = "";
                for(var key in data){
                    var text = document.createTextNode(data[key]["id"]);
                    spot.appendChild(text);
                    spot.appendChild(document.createElement("br"));
                    spot.appendChild(tableGen(data[key]["assemblers"]))
                    spot.appendChild(document.createElement("br"));
                    

                }

    
            })
        });
    //console.log([abs2,abs3])
    
}



