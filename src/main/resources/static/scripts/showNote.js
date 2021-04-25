async function showNote(event) {
    let url = event.target.href
    event.preventDefault()
    let response = await fetch(url, {
        method: "GET",
    })
    let note = await response.json()

    let l1 = document.getElementById("el1")
    l1.innerHTML=''
    let l2 = document.createElement("tr")
    let l3 = document.createElement("td")
    let l4 = document.createElement("td")
    let l5 = document.createElement("td")
    let l6 = document.createElement("td")
    let l7 = document.createElement("td")
    let l7a = document.createElement("a")
    l1.appendChild(l2)
    l2.appendChild(l3)
    l2.appendChild(l4)
    l2.appendChild(l5)
    l2.appendChild(l6)
    l2.appendChild(l7)
    l7.appendChild(l7a)
    l3.innerText = note.id
    l4.innerText = note.title
    l5.innerText = note.message
    l6.innerText = note.dateOfCreation
    l7a.setAttribute("href", note.links.href)
    l7a.innerText = "INFO"
}