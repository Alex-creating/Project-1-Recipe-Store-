$(document).ready(function() {
    $('.js-example-basic-multiple').select2({
        tags: true
    });
});


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

    
    JSON.stringify(newRecipe);
    axios.post('/RecipeStore/createRecipe', newRecipe)
    .then(function(response) {
        window.alert("You have added " + document.getElementById('recipeName').value + " to your store!");
        window.location = "/RecipeStore/RecipeViewAllPage.html";
    })
    .catch(function (error) {
        console.log(error);
    }); 
}

function changeToHomePage(){
    window.location = "/RecipeStore/index.html";
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
