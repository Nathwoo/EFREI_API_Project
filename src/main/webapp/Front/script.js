async function registerFilm(e) {
    e.preventDefault();

    // Collect form data
    const form = document.getElementById("filmForm");
    const formData = new FormData(form);

    // Create JSON object from form data
    const jsonObject = {};
    formData.forEach((value, key) => {
        jsonObject[key] = value;
    });

    // Display JSON object
    console.log(JSON.stringify(jsonObject));
}
