document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('.login-form'),
        signupForm = document.querySelector('.signup-form'),
        backLayer = document.querySelector('.back-layer');
    
    document.querySelector('.login button').addEventListener('pointerdown', () => {
        signupForm.classList.remove('active');
        loginForm.classList.add('active');
        backLayer.style.clipPath = '';
    });

    document.querySelector('.signup button').addEventListener('pointerdown', () => {
        loginForm.classList.remove('active');
        signupForm.classList.add('active');
        backLayer.style.clipPath = 'inset(0 0 0 50%)';
    });
});

function validateLogin() {
    let email = document.getElementById("lemail").value;
    let password = document.getElementById("lpassword").value;
    let valid = true;

    // Simple email regex for validation
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!email) {
        document.getElementById("lemailError").innerText = "This section must be filled in";
        valid = false;
    } else if (!emailRegex.test(email)) {
        document.getElementById("lemailError").innerText = "Invalid email format";
        valid = false;
    } else {
        document.getElementById("lemailError").innerText = "";
    }

    if (!password) {
        document.getElementById("lpasswordError").innerText = "This section must be filled in";
        valid = false;
    } else {
        document.getElementById("lpasswordError").innerText = "";
    }

    if (valid) {
        window.location.href = "../Home.html";
    }
}

function validateSignup() {
    let name = document.getElementById("name").value;
    let hp = document.getElementById("hp").value;
    let email = document.getElementById("semail").value;
    let password = document.getElementById("spassword").value;
    let valid = true;

    // Simple email regex for validation
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!name) {
        document.getElementById("nameError").innerText = "This section must be filled in";
        valid = false;
    } else {
        document.getElementById("nameError").innerText = "";
    }

    if (!hp) {
        document.getElementById("hpError").innerText = "This section must be filled in";
        valid = false;
    } else {
        document.getElementById("hpError").innerText = "";
    }

    if (!email) {
        document.getElementById("semailError").innerText = "This section must be filled in";
        valid = false;
    } else if (!emailRegex.test(email)) {
        document.getElementById("semailError").innerText = "Invalid email format";
        valid = false;
    } else {
        document.getElementById("semailError").innerText = "";
    }

    if (!password) {
        document.getElementById("spasswordError").innerText = "This section must be filled in";
        valid = false;
    } else {
        document.getElementById("spasswordError").innerText = "";
    }

    if (valid) {
        location.reload();
    }
}
