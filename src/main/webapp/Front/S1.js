document.getElementById('filmForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    let newFilm = {
        title: document.getElementById('title').value,
        duration: document.getElementById('duration').value,
        language: document.getElementById('language').value,
        subtitles: document.getElementById('subtitles').value,
        director: document.getElementById('director').value,
        mainActors: document.getElementById('mainActors').value,
        minAge: document.getElementById('minAge').value,
        startDate: document.getElementById('startDate').value,
        endDate: document.getElementById('endDate').value,
        daysOfWeek: document.getElementById('daysOfWeek').value,
        showTime: document.getElementById('showTime').value,
        city: document.getElementById('city').value,
        cinemaAddress: document.getElementById('cinemaAddress').value
    };

    try {
        let response = await fetch(`${window.API_URL}/film`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newFilm)
        });

        if (response.ok) {
            alert('Film soumis avec succès!');
        } else {
            alert('Une erreur s\'est produite lors de la soumission du film');
        }
    } catch (error) {
        console.error('Erreur:', error);
        alert('Une erreur s\'est produite lors de la soumission du film');
    }
});
