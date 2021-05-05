document.querySelector('#registerButton').addEventListener('click', register);

function register() {
    let un = document.querySelector('#username').value;
    let pw = document.querySelector('#psw').value;
    let email = document.querySelector('#email').value;
    let firstName = document.querySelector('#fName').value;
    let lastName = document.querySelector('#fName').value;

    let data = {
        username: un,
        password: pw,
        fName: firstName,
        lName: lastName,
        email: email
    };

    console.log(data)

    fetch('/register', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/login.html';
        } else if (response.status === 401) {
            displayDuplicateEmail()
        }
    })
}

function displayDuplicateEmail() {
    let email = document.querySelector('#email');

    let pElement = document.createElement('p');
    pElement.style.color = 'red';
    pElement.innerHTML = 'Duplicate username/email!<br>Please try again with a different email/username';
    pElement.style.textAlign = 'center';

    email.insertAdjacentElement("afterend", pElement);
}

let password = document.querySelector('#psw').value
let verifyPassword = document.querySelector('#verifypsw')

verifyPassword.addEventListener("input", (event) => {
    verify_passwords(password, verifyPassword.value)
})

function verify_passwords(password, verifyPassword) {
    if (verifyPassword != password) {
        let pElement = document.createElement('p');
        pElement.style.color = 'red';
        pElement.innerHTML = 'Passwords do not match. <br>Please check your password and try again!';
        pElement.style.textAlign = 'center';

        verifyPassword.insertAdjacentElement("afterend", pElement);
    }
}