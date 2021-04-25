async function checkForm() {
    let response = await fetch("/api/users")
    let users
    if (response.ok) {
        users = await response.json()
    } else {
        console.log(response.status)
    }

    let username = document.getElementById("username").value
    let password = document.getElementById("password").value
    let email = document.getElementById("email").value
    let confirmPassword = document.getElementById("confirm-password").value
    if (!username.match("[A-Za-z]+")) {
        alert("Username should not be empty!")
        return false
    }
    users.forEach(function (user) {
        if (user.username == username) {
            alert("Username already exists!")
            return false;
        }
        if (user.email == email) {
            alert("Email already exists!")
            return false;
        }
    })

    if (!password.match("[A-Za-z0-9]{8,}")) {
        alert("Password should contain at least 8 letters or digits!")
        return false
    }
    if (password == confirmPassword) {
        console.log("Password confirmed!")
        return true
    } else {
        alert("Password is not confirmed!")
        return false
    }
}

async function submitForm() {
    let form = document.getElementById("signup_form")
    if (await checkForm()) {
        form.submit()
    }
}