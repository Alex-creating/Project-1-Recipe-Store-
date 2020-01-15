const table = document.getElementById("allRecipesTableBody");
const recipeName = document.getElementById("singleRecipeName");
const recipeRating = document.getElementById("singleRecipeRating");
const recipeServing = document.getElementById("singleRecipeServing");
const recipeLength = document.getElementById("singleRecipeLength");
const recipeMethod = document.getElementById("singleRecipeMethod");
const recipeCategories = document.getElementById('singleRecipeCategories');
const recipeIngredients = document.getElementById('singleRecipeIngredients');

const currentCategories = document.getElementById('currentCategories');
const currentIngredients = document.getElementById('currentIngredients');


const deleteButton = document.getElementById("deleteButton");
const editButton = document.getElementById("editButton");

const editSubmitButton = document.getElementById("editSubmitButton");

const fiveStar = "&#9733;&#9733;&#9733;&#9733;&#9733;";
const fourStar = "&#9733;&#9733;&#9733;&#9733; ";
const threeStar = "&#9733;&#9733;&#9733; ";
const twoStar = "&#9733;&#9733; ";
const oneStar = "&#9733;";

$(document).ready(function() {
    $('.js-example-basic-multiple').select2({
        tags: true
    });
});



getRecipeAndCreateTable();


function getRecipeAndCreateTable() {
    axios.get("http://localhost:8080/getAllRec")
    .then(function(response) {
        console.log(response);
        addRecipeToTable(response.data);
        $('#allRecipesTableHead').DataTable(
            {"columns" : [
                null,
                null,
                null,
                null,
                {"orderable" : false },
                {"orderable" : false }
            ], 
            "paging" : false,
            "info" : false
        }
            
        );
    }).catch((error) => {
        console.log(error);
    });
}

function turnToInteger(stringObject){
    return Number.parseInt(stringObject);
}




function addRecipeToTable(recipeToAdd){ 
    for (let recipe of recipeToAdd) {
        
        let row = document.createElement("tr");
        
        let nameEntry = document.createElement("td");
        nameEntry.innerHTML = recipe.recipeName;
        row.appendChild(nameEntry);
        
        let ratingEntry = document.createElement("td");
        ratingEntry.innerHTML = starSystem(recipe);
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
        
        let categoryEntry = document.createElement("td");   
        categoryEntry.innerHTML = catAsString(recipe);
        row.appendChild(categoryEntry);

        let ingredientEntry = document.createElement("td"); 
        ingredientEntry.innerHTML = ingAsString(recipe);
        row.appendChild(ingredientEntry);
        
        table.appendChild(row); 

        row.addEventListener('click', ()=> getRecipeFromID(recipe.recipeId));
    }    
}

function catAsString(recipe){
    
    let result = '';

    if(recipe.categories.length == 0){
        return "";
    }

    else if(recipe.categories.length == 1){
        for(let i = 0; i < recipe.categories.length; i++ ){
            let a = recipe.categories;
            let b = a[i];
          
            result += b.categoryName;
        }
        
    }

    else{       
        result = recipe.categories[0].categoryName;

        for(let i = 1; i < recipe.categories.length; i++ ){
            let a = recipe.categories;
            let b = a[i];
     
            result += ", " + b.categoryName;
        }
        
    }
    return result;
}

function ingAsString(recipe){
    let ingResult = '';

    if(recipe.ingredients.length==0){ 
        return "";
    }
    else if(recipe.ingredients.length == 1){
        for(let i = 0; i < recipe.ingredients.length; i++ ){
            let a = recipe.ingredients;
            let b = a[i];
          
            ingResult += b.ingredientName;
            
        }
        
    }
    else{       
        ingResult = recipe.ingredients[0].ingredientName;
        for(let i = 1; i < recipe.ingredients.length; i++ ){
            let a = recipe.ingredients;
            let b = a[i];
     
            ingResult += ", " + b.ingredientName;
        }
        
    }
    return ingResult;
}

function starSystem(recipe){
    if (recipe.rating==1){
        return oneStar;
    }
    if (recipe.rating==2){
        return twoStar;
    }
    if (recipe.rating==3){
        return threeStar;
    }
    if (recipe.rating==4){
        return fourStar;
    }
    if (recipe.rating==5){
        return fiveStar;
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
    document.getElementById("editRecipeName").value = recipe.recipeName;
    document.getElementById("editRecipeRating").value = recipe.rating;
    document.getElementById("editRecipeServing").value = recipe.servingAmount;
    document.getElementById("editRecipeLength").value = recipe.timeToMake;
    document.getElementById("editRecipeMethod").value = recipe.method;

    currentIngredients.innerText = ingAsString(recipe);
    currentCategories.innerText = catAsString(recipe);

    editSubmitButton.addEventListener('click', ()=>editRecipe(recipe));
}


function populateViewPage(recipe) {
    recipeName.innerText = recipe.recipeName;
    recipeRating.innerText = recipe.rating;
    recipeServing.innerText = recipe.servingAmount;
    recipeLength.innerText = recipe.timeToMake;
    recipeMethod.innerText = recipe.method;
    recipeIngredients.innerText = ingAsString(recipe);
    recipeCategories.innerText = catAsString(recipe);

}

function editRecipe(recipe){

    let edittedRecipe = {
        recipeName : document.getElementById('editRecipeName').value,
        rating :turnToInteger(document.getElementById('editRecipeRating').value),
        servingAmount :turnToInteger(document.getElementById('editRecipeServing').value),
        timeToMake : turnToInteger(document.getElementById('editRecipeLength').value),
        method : document.getElementById('editRecipeMethod').value
    }
    JSON.stringify(edittedRecipe);


    axios.put('http://localhost:8080/updateRecipe/?recipeId=' + recipe.recipeId, edittedRecipe)
    .then(function(){ 
        axios.patch('http://localhost:8080/attachCategory/' +recipe.recipeId, getCategoryData())
        .then(function(){
             axios.patch('http://localhost:8080/attachIngredient/' +recipe.recipeId, getIngredientData())
             .then(function(){
                  window.location = "/RecipeViewAllPage.html";
             });
        });
    })
    .catch(function(error){
        console.log(error);
    });
}

function getCategoryData(){
    let results = $('#editCategory')[0].innerText.split("\n").map(c => { 
        return {
            "categoryName": c
        }
    });
    console.log(results);
    return results;
}
function getIngredientData(){
    let results = $('#editIngredient')[0].innerText.split("\n").map(i => { 
        return {
            "ingredientName": i
        }
    });
    
    // .map(function() { return { "ingredientName": $(this).val()} }).get();
    console.log(results);
    return results;
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
