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
            return data;
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
            window.location.href = '/';
        }
        location.reload();
    })
};

function renderTable(user) {
    let tbBody = document.querySelector("#tableBody");

    if (user.user_role.user_role_id == 1) {
        let submitButton = document.querySelector("#submit");
        submitButton.addEventListener("click", (event)=>{
            addReimbursement(user)
        });
        let add_reimb_button = document.createElement("button");
        add_reimb_button.innerHTML = "Add Reimbursement";
        add_reimb_button.id = "add_button";
        add_reimb_button.setAttribute("background-color", "#8458B3");
        add_reimb_button.setAttribute("color", "#ccc")
        add_reimb_button.setAttribute("width", "150px");
        add_reimb_button.setAttribute("float", "right");
        add_reimb_button.setAttribute("text-align", "center");
        add_reimb_button.setAttribute("margin-bottom", "10px");
        add_reimb_button.setAttribute("data-toggle", "modal");
        add_reimb_button.setAttribute("data-target", "#myModal");
        let tableDiv = document.querySelector("#tableDiv");

        tableDiv.insertAdjacentElement("beforebegin", add_reimb_button);

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
            let action_header = document.createElement("th");
            action_header.innerHTML = "Action";

            let status_header = document.querySelector("#status");
            status_header.insertAdjacentElement("afterend", action_header);

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
                    <td>${reimb_resolver}</td>`)
                if (reimb_status == 2) {
                    rowString = rowString.concat(`<td>Approved</td>`)
                } else if (reimb_status == 3) {
                    rowString = rowString.concat(`<td>Denied</td>`)
                } else {
                    rowString = rowString.concat(`<td>Pending</td>`)
                }
                if (reimb_status == 1) {
                    rowString = rowString.concat(`
                            <td><button type="button" class = "delete">-</button></td>
                        `)
                } else {
                    rowString = rowString.concat(`
                            <td> </td>
                        `)
                }
                rowString = rowString.concat("</tr>")
                tableData = tableData.concat(rowString)
                console.log(tableData)
                tbBody.innerHTML = tableData

                let delete_buttons = document.getElementsByClassName("delete");
                for (btn of delete_buttons) {
                    btn.addEventListener('click', (event) => {
                        deleteReimbursement(btn, user);
                    })
                };
            }
        })
    } else {
        let author_header = document.querySelector("#author")
        let resolver_header = document.querySelector("#resolver");
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
                    (reimb_submit_time[1] - 1),
                    reimb_submit_time[2],
                    reimb_submit_time[3],
                    reimb_submit_time[4],
                    reimb_submit_time[5]).toUTCString();
                let reimb_resolve_time = row.resolved;
                let resolved = null;
                if (reimb_resolve_time != null) {
                    resolved = new Date(reimb_resolve_time[0],
                        (reimb_resolve_time[1] - 1),
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
                    <td>${resolved}</td>`);
                if (reimb_status == 1) {
                    rowString = rowString.concat(`<td>
                        <button type = "button" class = "approve">\u2713</button>
                        <button type = "button" class = "deny">X</button>
                        </td>`);
                } else if (reimb_status == 2) {
                    rowString = rowString.concat(`<td>Approved</td>`);
                } else {
                    rowString = rowString.concat(`<td>Denied</td>`);
                }
                rowString = rowString.concat("</tr>")
                tableData = tableData.concat(rowString)
                tbBody.innerHTML = tableData

                approve_buttons = document.getElementsByClassName("approve");
                deny_buttons = document.getElementsByClassName("deny");
                for (btn of approve_buttons) {
                    btn.addEventListener('click', (event) => {
                        approveReimbursement(btn, user);
                    })
                };
                for (btn of deny_buttons) {
                    btn.addEventListener('click', (event) => {
                        denyReimbursement(btn, user);
                    })
                };
            }
        })
    }
}

function approveReimbursement(btn, user) {
    var td = btn.parentElement
    var tr = td.parentNode
    var reimb_id = tr.firstChild.innerHTML

    console.log(user);

    let data = {
        id: Number(reimb_id),
        status_id: 2,
        resolver: user.userID
    };

    fetch('http://localhost:7000/reimbursements', {
        method: 'PUT',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            td.removeChild(btn)
            td.innerHTML = "Accepted"
        }
    })
    location.reload();
}

function denyReimbursement(btn, user) {
    var td = btn.parentElement
    var tr = td.parentNode
    var reimb_id = tr.firstChild.innerHTML

    let data = {
        id: Number(reimb_id),
        status_id: 3,
        resolver: user.userID
    };

    fetch('http://localhost:7000/reimbursements', {
        method: 'PUT',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            td.removeChild(btn)
            td.innerHTML = "Rejected"
        }
    })
    location.reload();
}

function deleteReimbursement(btn, user) {
    var td = btn.parentElement
    var tr = td.parentNode
    var reimb_id = tr.firstChild.innerHTML

    let data = {
        id: Number(reimb_id),
        author: user.userID
    };

    fetch('http://localhost:7000/reimbursements', {
        method: 'DELETE',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            location.reload();
        }
    })
}

function addReimbursement(user) {
    console.log(document.querySelector("#amount").value)

    let reimb_amount = document.querySelector("#amount").value;
    let reimb_type = document.querySelector("#type").value;
    let reimb_description = null;
    if(document.querySelector("#desc").value != null){
        reimb_description = document.querySelector("#desc").value; 
    }
    let reimb_receipt = null; 
    if(document.querySelector("#receipt").value != ""){
        console.log(document.querySelector("#receipt").value)
        reimb_receipt = document.querySelector("receipt").value;
    }
    let data = {
        amount: reimb_amount,
        type: reimb_type,
        description: reimb_description,
        receipt: reimb_receipt,
        submitter: user.userID
    }

    fetch('http://localhost:7000/reimbursements/add', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            location.reload();
        }
    })

}