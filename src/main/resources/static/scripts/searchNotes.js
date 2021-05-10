// function getInputValue() {
//     // Selecting the input element and get its value
//     var inputVal = document.getElementById("searchInput").value;
//     return inputVal
//     // Displaying the value
//     // alert(inputVal);
// }

async function searchNotes() {
    var inputVal = document.getElementById("searchInput").value;
    let response = await fetch("/api/notes/search?word=" + inputVal)
    let notes
    if (response.ok) {
        notes = await response.json()
        console.log(notes)
    } else {
        console.log(response.status)
    }
    let l1 = document.getElementById("el1")
    l1.innerHTML=''
    for (let i = 0; i < notes.length; i++) {
        let l1 = document.getElementById("el1")
        let l2 = document.createElement("tr")
        let l3 = document.createElement("td")
        let l4 = document.createElement("td")
        let l5 = document.createElement("td")
        let l6 = document.createElement("td")
        let l7 = document.createElement("td")
        let l7a = document.createElement("a")
        let l8 = document.createElement("td")
        let l8a = document.createElement("a")
        let l9 = document.createElement("td")
        let l9a = document.createElement("a")
        l1.appendChild(l2)
        l2.appendChild(l3)
        l2.appendChild(l4)
        l2.appendChild(l5)
        l2.appendChild(l6)
        l2.appendChild(l7)
        l7.appendChild(l7a)
        l2.appendChild(l8)
        l8.appendChild(l8a)
        l2.appendChild(l9)
        l9.appendChild(l9a)
        l3.innerText = notes[i].id
        l4.innerText = notes[i].title
        l5.innerText = notes[i].message
        l6.innerText = notes[i].dateOfCreation
        l7a.setAttribute("href", notes[i].links[0].href)
        l7a.innerText = "INFO"
        l7a.onclick= showNote
        l8a.setAttribute("href", notes[i].links[1].href)
        l8a.onclick = deleteNote
        l8a.innerText = "DELETE"
        l9a.setAttribute("href", "#")

        l9a.innerText = "EDIT"
    }

}