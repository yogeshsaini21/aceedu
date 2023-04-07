const redirectBtn = document.getElementById('redirect-btn');
const redirectBtn1 = document.getElementById('redirect-btn1');



// add click event listener to the button
redirectBtn.addEventListener('click', () => {

  window.location.href = 'bulkuser.html';
});

redirectBtn1.addEventListener('click', () => {

  window.location.href = 'bulktestResult.html';
});

