document.querySelector('#registerButton').addEventListener('click', login);

function login() {
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
            displayInvalidLogin();
        }
    })
}

function displayInvalidLogin() {
    let passwordButton = document.querySelector('#password');

    let pElement = document.createElement('p');
    pElement.style.color = 'red';
    pElement.innerHTML = 'Invalid login!<br>Please check your Username and Password and try again.';
    pElement.style.textAlign = 'center';  

    passwordButton.appendChild(pElement);
}