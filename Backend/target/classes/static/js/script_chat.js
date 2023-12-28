document.querySelectorAll('[data-conversation]').forEach(function(item) {
    item.addEventListener('click', function(e) {
        e.preventDefault()
        document.querySelectorAll('.conversation').forEach(function(i) {
            i.classList.remove('active')
        })
        document.querySelector(this.dataset.conversation).classList.add('active')
    })
})

document.querySelectorAll('.conversation-back').forEach(function(item) {
    item.addEventListener('click', function(e) {
        e.preventDefault()
        this.closest('.conversation').classList.remove('active')
        document.querySelector('.conversation-default').classList.add('active')
    })
})

function sendPostRequest(urlData, json) {

    // IP = 192.168.0.*
    // PORT = 8080
    // urlData = /user/create

    return fetch(IP+PORT+urlData, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(json)
    })
     .then(response => response.json())
     .then(response => console.log(JSON.stringify(response)));
  }
  
  // const url = 'https://reqbin.com/echo/post/json';
  // const json = { "id": 78912 };
  
  const response = sendPostRequest(url, json);
