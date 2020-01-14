let recipeValue = document.getElementById('recipeName').value;


const pattern = "[a-zA-Z0-9 ]{3-50}";

function checkRecipeDetails() {

    

}

function checkRecipeName(){
    let result = recipeValue.match(pattern);

    if (result == false){
        alert("Wrong");
        return false;
    }
}