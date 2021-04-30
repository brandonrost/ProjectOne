window.onload = function() {
   let current_user = renderCurrentUser(); 
};

function renderCurrentUser(){
    fetch('http://localhost:7000/current-user', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if(response.status === 400){
            return null; 
        }
        return response.json();
    }).then((data) => {
        if (data != null && document.querySelector('#login').innerHTML.startsWith('<a')){
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
            
        } 
    })
};

function logout(){
    sessionStorage.clear(); 
    fetch('http://localhost:7000/logout', {
        method: 'POST',
        credentials: 'include'
    }).then((response) => {
        return response.json()
    }).then((data) => {
        if(data == "User logged out successfully!"){
            let userGreeting = document.querySelector('#userGreeting');
            userGreeting.remove(); 
            let loginNav = document.querySelector('#login')
            loginNav.innerHTML = `<a href="/login.html">Login</a>`;
        }
        location.reload(); 
    })
};