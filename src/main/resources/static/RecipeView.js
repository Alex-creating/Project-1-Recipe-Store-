const table = document.getElementById("allRecipesTableBody");
const recipeName = document.getElementById("singleRecipeName");
const recipeRating = document.getElementById("singleRecipeRating");
const recipeServing = document.getElementById("singleRecipeServing");
const recipeLength = document.getElementById("singleRecipeLength");
const recipeMethod = document.getElementById("singleRecipeMethod");

const deleteButton = document.getElementById("deleteButton");
const editButton = document.getElementById("editButton");

const editName = document.getElementById("editRecipeName");
const editRating = document.getElementById("editRecipeRating");
const editServing = document.getElementById("editRecipeServing");
const editLength = document.getElementById("editRecipeLength");
const editMethod = document.getElementById("editRecipeMethod");

const fiveStar = "&#9733;&#9733;&#9733;&#9733;&#9733;";
const fourStar = "&#9733;&#9733;&#9733;&#9733; ";
const threeStar = "&#9733;&#9733;&#9733; ";
const twoStar = "&#9733;&#9733; ";
const oneStar = "&#9733;";

getRecipeAndCreateTable();


function getRecipeAndCreateTable() {
    axios.get("http://localhost:8080/getAllRec")
    .then(function(response) {
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
        if (recipe.rating==1){
            ratingEntry.innerHTML = oneStar;
        }
        if (recipe.rating==2){
            ratingEntry.innerHTML = twoStar;
        }
        if (recipe.rating==3){
            ratingEntry.innerHTML = threeStar;
        }
        if (recipe.rating==4){
            ratingEntry.innerHTML = fourStar;
        }
        if (recipe.rating==5){
            ratingEntry.innerHTML = fiveStar;
        }
        row.appendChild(ratingEntry);
        
        let servingEntry = document.createElement("td");
        if (recipe.servingAmount == 1){
            servingEntry.innerHTML = recipe.servingAmount + " person";
        }
        else {
            servingEntry.innerHTML = recipe.servingAmount + " people";
        }
        row.appendChild(servingEntry);
        
        let lengthEntry = document.createElement("td");
        lengthEntry.innerHTML = recipe.timeToMake + " minutes";
        row.appendChild(lengthEntry);
        table.appendChild(row); 
     
        row.addEventListener('click', ()=> getRecipeFromID(recipe.recipeId));
        
    }      
    
}

function getRecipeFromID(id){
    axios.get("http://localhost:8080/getRec/" + id)
    .then(function(response) {
        populateViewPage(response.data);
        $("#TablePage").toggle();
        $("#ViewPage").toggle();
        deleteButton.addEventListener('click', ()=> deleteRecipe(response.data));
        editButton.addEventListener('click', ()=> populateEditRecipe(response.data));
        editButton.addEventListener('click', ()=> changeToRecipePage());
    }).catch((error) => {
        console.log(error);
    });
}

function populateEditRecipe(recipe){
    editName.innerText = recipe.recipeName;
    editRating.innerText = recipe.rating;
    editServing.innerText = recipe.servingAmount;
    editLength.innerText = recipe.timeToMake;
    editMethod.innerText = recipe.method;
}


function populateViewPage(recipe) {
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

function changeToRecipePage(){
    $("#ViewPage").toggle();
    $("#editPage").toggle();

}

function deleteRecipe(recipe){
    if (confirm("Are you sure you want to delete " + recipe.recipeName + " from your store?")){
        axios.delete("http://localhost:8080/deleteRecipe/" + recipe.recipeId)
        .then(function() {
            alert('\t Successfully Deleted!');
            window.location = "/RecipeViewAllPage.html";
        })
        .catch(function(error){
            console.log(error);
        });
    }
    return false;
}

function changeToHomePage(){
    window.location = "/index.html";
}
