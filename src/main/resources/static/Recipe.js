$(document).ready(function() {
    $('.js-example-basic-multiple').select2({
        tags: true
    });
});

// var textContainer, textareaSize, input;
// var autoSize = function () {
//   // also can use textContent or innerText
//   textareaSize.innerHTML = input.value + '\n';
// };

// document.addEventListener('DOMContentLoaded', function() {
//   textContainer = document.querySelector('.textarea-container');
//   textareaSize = textContainer.querySelector('.textarea-size');
//   input = textContainer.querySelector('textarea');

//   autoSize();
//   input.addEventListener('input', autoSize);
// });

function turnToInteger(stringObject){
    return Number.parseInt(stringObject);
}

function addRecipe(){

    let newRecipe = {  
        recipeName : document.getElementById('recipeName').value, 
        rating :turnToInteger(document.getElementById('Rating').value),
        servingAmount :turnToInteger(document.getElementById('serving').value),
        timeToMake : turnToInteger(document.getElementById('howLong').value),
        method : document.getElementById('method').value,
        categories : getCategoryDataBack(),
        ingredients : getIngredientDataBack()

    };

    
    let stringed = JSON.stringify(newRecipe);
    axios.post('http://localhost:8080/createRecipe', stringed)
    .then(function(response) {
        window.alert("You have added " + document.getElementById('recipeName').value + " to your store!");
        window.location = "/RecipeViewAllPage.html";
    })
    .catch(function (error) {
        console.log(error);
    }); 
}

function changeToHomePage(){
    window.location = "/index.html";
}

function getCategoryDataBack(){
    var cate = $('#categoryInputer').map(function() { return $(this).val(); }).get();
    console.log(cate);
    return cate;
}
function getIngredientDataBack(){
    var ingre = $('#ingredientInputer').map(function() { return $(this).val(); }).get();
    console.log(ingre);
    return ingre;
}


// function getCategoryData(){
//     var results = $('#category').map(function() { return  { "categoryName": $(this).val()} }).get();
//     console.log(results);
//     return results;
// }
// function getIngredientData(){
//     let results = $('#ingredient').map(function() { return { "ingredientName": $(this).val()} }).get();
//     console.log(results);
//     return results;
// }

// function patchInCategory(recipe){
//     let catStringed = JSON.stringify(getCategoryData());
//     axios.patch('http://localhost:8080/attachCategory/' +recipe.recipeId, catStringed);
    
// }

// function patchInIngredient(recipe){  
//     let ingStringed = JSON.stringify(getIngredientData());
//     axios.patch('http://localhost:8080/attachIngredient/' +recipe.recipeId, ingStringed);
// }