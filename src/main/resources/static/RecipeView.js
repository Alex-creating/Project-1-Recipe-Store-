
const table = document.getElementById("allRecipesTableBody");

function getRecipeAndCreateTable() {
    axios.get("http://localhost:8080/getAllRec")
    .then(function(response) {
        console.log(response.data);
        addRecipeToTable(response.data);
    }).catch((error) => {
        console.log(error);
    });
}
getRecipeAndCreateTable();
// window.onload = getRecipeAndCreateTable;

function addRecipeToTable(recipeToAdd){ 
    for (let recipe of recipeToAdd) {

        let row = document.createElement("tr");
        let nameEntry = document.createElement("td");
        nameEntry.innerHTML = recipe.recipeName;
        row.appendChild(nameEntry);
        
        let ratingEntry = document.createElement("td");
        ratingEntry.innerHTML = recipe.rating;
        row.appendChild(ratingEntry);
        
        let servingEntry = document.createElement("td");
        servingEntry.innerHTML = recipe.servingAmount;
        row.appendChild(servingEntry);
        
        let lengthEntry = document.createElement("td");
        lengthEntry.innerHTML = recipe.timeToMake;
        row.appendChild(lengthEntry);
        
        let methodEntry = document.createElement("td");
        methodEntry.innerHTML = recipe.method;
        row.appendChild(methodEntry);
        
        table.appendChild(row);
    }
}


function getAllRecipes(){

    axios.get('http://localhost:8080/getAllRec')
    
        .then((response) => {
            console.log(response);

        })
        .catch(function (error) {
            console.log(error);
        });
    }
