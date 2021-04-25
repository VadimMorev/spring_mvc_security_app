async function deleteNote(event) {

    if (confirm('Delete?')) {
        // let id = this.id
        let url = event.target.href
        event.preventDefault()
        let response = await fetch(url, {
            method: "DELETE"
        })
        let jsonAnswer = await response.json()
        getNotes()
        return jsonAnswer
    }
}