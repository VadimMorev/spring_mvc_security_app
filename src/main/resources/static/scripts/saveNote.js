async function saveNote() {
    let title = document.getElementById("note-title").value
    let text = document.getElementById("note-text").value
    let  note = {title: title, message: text}
    let response = await fetch("/api/notes", {
        method: "POST",
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        body: JSON.stringify(note)
    })
    let jsonAnswer = await response.json()
    getNotes()
    return jsonAnswer
}
