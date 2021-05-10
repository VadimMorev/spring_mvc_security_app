async function getUsers() {
    let response = await fetch("/api/users/")
    let users
    if (response.ok) {
        users = await response.json()
        console.log(users)
    } else {
        console.log(response.status)
    }
    let l1 = document.getElementById("el1")
    l1.innerHTML=''
    for (let i = 0; i < users.length; i++) {
        let l1 = document.getElementById("el1")
        let l2 = document.createElement("tr")
        let l3 = document.createElement("td")
        let l4 = document.createElement("td")
        let l5 = document.createElement("td")
        let l6 = document.createElement("td")
        let l7 = document.createElement("td")
        // let l7a = document.createElement("a")
        let l8 = document.createElement("td")
        // let l8a = document.createElement("a")
        let l9 = document.createElement("td")
        let l10 = document.createElement("td")
        let l10a = document.createElement("a")
        l1.appendChild(l2)
        l2.appendChild(l3)
        l2.appendChild(l4)
        l2.appendChild(l5)
        l2.appendChild(l6)
        l2.appendChild(l7)
        l2.appendChild(l8)
        l2.appendChild(l9)
        l2.appendChild(l10)
        l10.appendChild(l10a)
        l3.innerText = users[i].id
        l4.innerText = users[i].username
        l5.innerText = users[i].role
        l6.innerText = users[i].age
        l10a.setAttribute("href", users[i].links[0].href)
        l10a.innerText = "BAN"
        l10a.onclick = banUser
        l7.innerText = users[i].firstname
        // l7a.onclick= showNote
        // l8a.setAttribute("href", users[i].links[1].href)
        // l8a.onclick = deleteNote
        l8.innerText = users[i].lastname
        // l9a.setAttribute("href", "#")

        l9.innerText = users[i].enabled
    }
}
getUsers()