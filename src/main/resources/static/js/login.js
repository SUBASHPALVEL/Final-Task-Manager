function validateForm() {
    var usermail = document.getElementById("usermail").value;
    var password = document.getElementById("password").value;

    if (usermail === "" || password === "") {
        alert("Email and password are required");
        return false;
    }

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(usermail)) {
        alert("Invalid email address");
        return false;
    }

    if (password.length < 8 || password.length > 15) {
        alert("Password must contain 8 to 15 characters");
        return false;
    }


    return true;
}

