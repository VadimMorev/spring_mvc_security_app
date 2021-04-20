function checkNotesCreateForm() {
    let title = document.getElementById("note-title").value
    let text = document.getElementById("note-text").value
    if (!title.match("[A-Za-z0-9]+")) {
        alert("Title should not be empty!")
        return false
    }
    if (!text.match("[A-Za-z0-9]+")) {
        alert("Text should not be empty!")
        return false
    }
    return true
}