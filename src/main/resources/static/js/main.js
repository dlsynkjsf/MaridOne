const loginButton = document.getElementById("login-button");

async function login() {
    const usernameInput = document.getElementById("input-username");
    const passwordInput = document.getElementById("input-password");


    try {
        const response = await fetch("/process_login", {
            method: 'POST'
        });
    } catch {

    }

}
