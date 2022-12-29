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
        { name: "Monte Falco", job: "Coding", time: "01:00-02:00" },
        { name: "Monte Falterona", job: "Read", time: "01:00-02:00" },
        { name: "Poggio Scali", job: "ghh", time: "01:00-02:00" },
        { name: "Pratomagno", job: "aasdas", time: "01:00-02:00" },
        { name: "Monte Amiata", job: "asdsad", time: "01:00-02:00" }
      ];
    
    function tableCreate() {

        // fetch the data from the server 

        let col = [];
        for (let i = 0; i < inf.length; i++) {
        for (let key in inf[i]) {
            if (col.indexOf(key) === -1) {
            col.push(key);
            }
        }
        }

        // Create a table.
        const table = document.createElement("table");

        // Create table header row using the extracted headers above.
        let tr = table.insertRow(-1);                   // table row.

        for (let i = 0; i < col.length; i++) {
        let th = document.createElement("th");      // table header.
        th.innerHTML = col[i];
        tr.appendChild(th);
        }

        // add json data to the table as rows.
        for (let i = 0; i < inf.length; i++) {

        tr = table.insertRow(-1);

        for (let j = 0; j < col.length; j++) {
            let tabCell = tr.insertCell(-1);
            tabCell.innerHTML = inf[i][col[j]];
        }
        }

        const divShowData = document.getElementById('tableSpot');
        divShowData.innerHTML = "";
        divShowData.appendChild(table);
}
      
    