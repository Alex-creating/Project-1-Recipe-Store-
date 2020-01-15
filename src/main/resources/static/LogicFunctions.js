let recipeValue = document.getElementById('recipeName').value;



function checkRecipeDetails() {

}

function checkRecipeName(){
    if (document.getElementById("recipeName").value.length <3){
        wrongField("recipeName");
        alert("Please enter a name longer than 2 characters");
        window
    }
    if (document.getElementById("recipeName").value.length >50){
        wrongField("recipeName");

    }

}

function wrongField(whatIsWrong){
    document.getElementById(whatIsWrong).style.backgroundColor = "#FF8383";
    return false;
}