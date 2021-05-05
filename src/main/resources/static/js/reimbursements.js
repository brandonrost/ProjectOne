window.onload = function () {
    let user = renderCurrentUser();
};

function renderCurrentUser() {
    fetch('http://localhost:7000/current-user', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 400) {
            return null;
        }
        return response.json();
    }).then((data) => {
        if (data != null && document.querySelector('#login').innerHTML.startsWith('<a')) {
            let navList = document.querySelector('#navbarList')
            let username = data.username;
            let fName = data.firstName;
            let user_role = data.user_role.user_role_id;

            let userGreeting = document.createElement('li');
            userGreeting.setAttribute('id', 'userGreeting');
            userGreeting.setAttribute('font-size', '12px');
            userGreeting.innerHTML = `Hello ${fName}!<br>Welcome Back ${username}`;
            navList.append(userGreeting);

            let loginNav = document.querySelector('#login');
            let button = document.createElement('button');
            button.id = 'logout';
            button.setAttribute('background-color', 'none');
            button.setAttribute('border', 'none');
            button.setAttribute('color', 'rgb(255, 255, 255)');
            button.setAttribute('font-size', '20px');
            button.addEventListener('click', logout);
            button.innerHTML = `Logout`;

            loginNav.innerHTML = '';
            loginNav.appendChild(button);

            renderTable(data);
        }
    })
};

function logout() {
    sessionStorage.clear();
    fetch('http://localhost:7000/logout', {
        method: 'POST',
        credentials: 'include'
    }).then((response) => {
        return response.json()
    }).then((data) => {
        if (data == "User logged out successfully!") {
            let userGreeting = document.querySelector('#userGreeting');
            userGreeting.remove();
            let loginNav = document.querySelector('#login')
            loginNav.innerHTML = `<a href="/login.html">Login</a>`;
        }
        location.reload();
    })
};

function renderTable(user) {
    let tbBody = document.querySelector("#tableBody");

    if (user.user_role.user_role_id == 1) {
        fetch('/reimbursements', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: user
        }).then((response) => {
            return response.json()
        }).then((data) => {
            console.log(data)
            let tableData = ""
            for (row of data) {
                console.log(row)
                let rowString = "";
                rowString = rowString.concat("<tr>")
                let reimb_id = row.id;
                let reimb_amount = row.amount;
                let reimb_type = row.type;
                let reimb_description = row.description;
                let reimb_receipt = row.receipt;
                let reimb_author = row.author;
                let reimb_resolver = row.resolver;
                let reimb_status = row.status;
                rowString = rowString.concat(`<td>${reimb_id}</td>
                    <td>${reimb_amount}</td>
                    <td>${reimb_type}</td>
                    <td>${reimb_description}</td>
                    <td>${reimb_receipt}</td>
                    <td>${reimb_author}</td>
                    <td>${reimb_resolver}</td>
                    <td>${reimb_status}</td>`)
                rowString = rowString.concat("</tr>")
                tableData = tableData.concat(rowString)
                console.log(tableData)
                tbBody.innerHTML = tableData
            }
        })
    } else {
        let author_header = document.querySelector("#author")
        let resolver_header = document.querySelector("#resolver");
        let table_headers = document.querySelector("#tableHeaders");
        let submitted_header = document.createElement("th");
        let resolved_header = document.createElement("th");

        submitted_header.innerHTML = "Submitted";
        resolved_header.innerHTML = "Resolved";

        author_header.insertAdjacentElement('afterend', submitted_header);
        resolver_header.insertAdjacentElement('afterend', resolved_header);

        fetch('/reimbursements', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: user
        }).then((response) => {
            return response.json()
        }).then((data) => {
            console.log(data)
            let tableData = ""
            for (row of data) {
                console.log(row)
                let rowString = "";
                rowString = rowString.concat("<tr>")
                let reimb_id = row.id;
                let reimb_amount = row.amount;
                let reimb_submit_time = row.submitted;
                let submitted = new Date(reimb_submit_time[0],
                    (reimb_submit_time[1]-1),
                    reimb_submit_time[2],
                    reimb_submit_time[3],
                    reimb_submit_time[4],
                    reimb_submit_time[5]).toUTCString();
                let reimb_resolve_time = row.resolved;
                let resolved = null;
                if (reimb_resolve_time != null) {
                    resolved = new Date(reimb_resolve_time[0],
                        (reimb_resolve_time[1]-1),
                        reimb_resolve_time[2],
                        reimb_resolve_time[3],
                        reimb_resolve_time[4],
                        reimb_resolve_time[5]).toUTCString();
                }
                let reimb_description = row.description;
                let reimb_receipt = row.receipt;
                let reimb_author = row.author;
                let reimb_resolver = row.resolver;
                let reimb_status = row.status;
                let reimb_type = row.type;
                rowString = rowString.concat(`<td>${reimb_id}</td>
                    <td>${reimb_amount}</td>                    
                    <td>${reimb_type}</td>
                    <td>${reimb_description}</td>
                    <td>${reimb_receipt}</td>
                    <td>${reimb_author}</td>
                    <td>${submitted}</td>
                    <td>${reimb_resolver}</td>
                    <td>${resolved}</td>
                    <td>${reimb_status}</td>`)
                rowString = rowString.concat("</tr>")
                tableData = tableData.concat(rowString)
                console.log(tableData)
                tbBody.innerHTML = tableData
            }
        })
    }
}