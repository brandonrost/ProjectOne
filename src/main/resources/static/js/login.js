document.querySelector('#submitButton').addEventListener('click', login);

function login() {
    let un = document.querySelector('#username').value;
    let pw = document.querySelector('#password').value;

    let data = {
        username: un,
        password: pw
    };

    console.log(data)

    fetch('http://localhost:7000/login', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/';
        } else if (response.status === 401) {
            displayInvalidLogin();
        }
    })
}

function displayInvalidLogin() {
    let password = document.querySelector('#password');

    let pElement = document.createElement('p');
    pElement.style.color = 'red';
    pElement.innerHTML = 'Invalid login!<br>Please check your Username and Password and try again.';
    pElement.style.textAlign = 'center';

    password.insertAdjacentElement("afterend",pElement);
}