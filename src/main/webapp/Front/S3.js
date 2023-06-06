document.getElementById('searchMovie').addEventListener('submit', async function (e) {
    e.preventDefault();

    let title = document.getElementById('title').value;

    try {
        let response = await fetch(`${window.API_URL}/movies?title=${title}`);
        if (response.ok) {
            let movie = await response.json();

            let resultElement = document.getElementById('results');
            resultElement.innerHTML = ''; // Effacer les résultats précédents
            for (let detail in movie) {
                let listItem = document.createElement('li');
                listItem.textContent = `${detail}: ${movie[detail]}`;
                resultElement.appendChild(listItem);
            }
        } else {
            alert(`Erreur: ${response.statusText}`);
        }
    } catch (error) {
        console.error('Erreur:', error);
        alert('Une erreur s\'est produite lors de la récupération des détails du film.');
    }
});
