const container = document.getElementById('conteiner');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    const response = sendPostRequest("/user/create", initJSON());
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

function initJSON() {
    return {
        "login": document.getElementById("nickname").value,
        "password": encrypt(document.getElementById("password").value),
        "name": document.getElementById("name").value,
        "surname": document.getElementById("surname").value
    };
}
function sendPostRequest(urlData) {

    // IP = 192.168.0.*
    // PORT = 8080
    // urlData = /user/create


    return fetch(IP+PORT+urlData, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(initJSON())
    })
        .then(response => response.json())
        .then(response => console.log(JSON.stringify(response)));
}

// const url = 'https://reqbin.com/echo/post/json';
// const json = { "id": 78912 };

// const response = sendPostRequest("/user/create", initJSON());