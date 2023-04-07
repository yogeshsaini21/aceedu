function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
  
    if (username === "techking@gmail.com" && password === "techking@123") {
      window.location.href = "admin.html";
    } else if(username === "techkinguser@gmail.com" && password === "techkinguser@123") {
      window.location.href = "user.html";
    }
    else{
      alert("user email or password are Incorrect !!")
    }
    }
  
  