
const table = document.getElementById("allRecipesTableBody");
const recipeName = document.getElementById("singleRecipeName");
const recipeRating = document.getElementById("singleRecipeRating");
const recipeServing = document.getElementById("singleRecipeServing");
const recipeLength = document.getElementById("singleRecipeLength");
const recipeMethod = document.getElementById("singleRecipeMethod");



getRecipeAndCreateTable();


function getRecipeAndCreateTable() {
    axios.get("http://localhost:8080/getAllRec")
    .then(function(response) {
        console.log(response.data);
        addRecipeToTable(response.data);
    }).catch((error) => {
        console.log(error);
    });
}


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
        
        table.appendChild(row); 
        console.log("hello");
     
        row.addEventListener('click', ()=> getRecipeFromID(recipe.recipeId));
        
    }      
    
}

function getRecipeFromID(id){
    axios.get("http://localhost:8080/getRec/" + id)
    .then(function(response) {
        populatePage(response.data);
        $("#TablePage").toggle();
        $("#ViewPage").toggle();
     
    }).catch((error) => {
        console.log(error);
    });
}




function populatePage(recipe) {
    recipeName.innerText = recipe.recipeName;
    recipeRating.innerText = recipe.rating;
    recipeServing.innerText = recipe.servingAmount;
    recipeLength.innerText = recipe.timeToMake;
    recipeMethod.innerText = recipe.method;
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

