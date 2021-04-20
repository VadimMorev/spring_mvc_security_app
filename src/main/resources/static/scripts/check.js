function checkForm() {
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value
    let confirmPassword = document.getElementById("confirm-password").value
    if(!username.match("[A-Za-z]+")){
        alert("Username should not be empty!")
        return false
    }
    if(!password.match("[A-Za-z0-9]{8,}")){
        alert("Password should contain at least 8 letters or digits!")
        return false
    }
    if (password==confirmPassword){
        console.log("Password confirmed!")
        return true
    }else{
        alert("Password is not confirmed!")
        return false
    }
}