document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    try {
        let response = await fetch(`${window.API_URL}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });


        if (response.ok) {
            alert('Connexion réussie !');
            // Redirige vers service1.html après une connexion réussie
            window.location.href = 'service1.html';
        } else {
            alert('Échec de la connexion. Veuillez vérifier vos identifiants.');
        }
    } catch (error) {
        console.error('Erreur:', error);
        alert('Une erreur s\'est produite lors de la tentative de connexion.');
        // Redirige vers service1.html après une connexion raté pour le dev
        window.location.href = 'service1.html';
    }
});
