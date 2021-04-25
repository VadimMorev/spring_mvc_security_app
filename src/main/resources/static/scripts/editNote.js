async function editNote() {
    let id = document.getElementById("edit-id").value
    let title = document.getElementById("edit-title").value
    let text = document.getElementById("edit-text").value
    let  note = {id: id, title: title, message: text}
    let response = await fetch("/api/notes", {
        method: "PUT",
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        body: JSON.stringify(note)
    })
    let jsonAnswer = await response.json()
    getNotes()
    return jsonAnswer
}
