document.getElementById('searchCity').addEventListener('submit', async function (e) {
    e.preventDefault();

    let city = document.getElementById('city').value;

    try {
        let response = await fetch(`${window.API_URL}/movies?city=${city}`);

        if (response.ok) {
            let movies = await response.json();

            let resultElement = document.getElementById('results');
            resultElement.innerHTML = ''; // Effacer les résultats précédents
            for (let movie of movies) {
                let listItem = document.createElement('li');
                listItem.textContent = movie.title;
                resultElement.appendChild(listItem);
            }
        } else {
            alert(`Erreur: ${response.statusText}`);
        }
    } catch (error) {
        console.error('Erreur:', error);
        alert('Une erreur s\'est produite lors de la récupération des films.');
    }
});
