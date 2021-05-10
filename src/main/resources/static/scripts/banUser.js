async function banUser(event) {

    if (confirm('Change user status?')) {
        // let id = this.id
        let url = event.target.href
        event.preventDefault()
        let response = await fetch(url, {
            method: "PUT"
        })
        let jsonAnswer = await response.json()
        getUsers()
        return jsonAnswer
    }
}