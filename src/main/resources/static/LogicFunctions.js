
function checkRecipeDetails(){

    correctField("recipeName");
    correctField("serving");
    correctField("howLong");

    if (checkRecipeName("recipeName")){
        return false;
    }
    else if (checkServing("serving")){
        return false;
    }
    else if (checkLength("howLong")){
        return false;
    }
    else{
        addRecipe();
    }
}

function checkEditDetails(recipe){
    correctField("editRecipeName");
    correctField("editRecipeServing");
    correctField("editRecipeLength");

    if (checkRecipeName("editRecipeName")){
        return false;
    }
    else if (checkServing("editRecipeServing")){
        return false;
    }
    else if (checkLength("editRecipeLength")){
        return false;
    }
    else{
        editRecipe(recipe);
    }
}

function checkRecipeName(addOrEdit){
    let recipeValue = document.getElementById(addOrEdit).value;
    var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
    var numChars = "0123456789";
    var alphaChars = "abcdefghijklmnopqrstuvwxyz";

    if (recipeValue.length <3){
        wrongField(addOrEdit);
        alert("Please enter a name longer than 2 characters");
        return true;
    }
    if (recipeValue.length >50){
        wrongField(addOrEdit);
        alert("Please enter a name shorter than 50 characters");
        return true;
    }
    
    for (var i = 0; i < recipeValue.length; i++) {
    if (iChars.indexOf(recipeValue.charAt(i)) != -1) {
        wrongField(addOrEdit);
        alert ("Your recipe cannot contain special characters");
        return true;
    }
    for (var i = 0; i < recipeValue.length; i++) {
        if (iChars.indexOf(recipeValue.charAt(i)) != -1) {
            wrongField(addOrEdit);
            alert ("Your recipe cannot contain special characters");
            return true;
        } 
}
    }
    var count = 0;

    for (var k = 0; k < recipeValue.length; k++) {
        if (numChars.indexOf(recipeValue.charAt(k)) != -1) {
            for (var j = 0; j < recipeValue.length; j++) {
                if (alphaChars.indexOf(recipeValue.charAt(j)) != -1) {
                    count++;
                }
            }
                if(count == 0){
                    wrongField(addOrEdit);
                    alert ("Your recipe cannot contain only numbers");
                    return true;
                
            }
        }
    }

    return false;
}

function checkServing(addOrEdit){
    let servingValue = document.getElementById(addOrEdit).value;

    if (servingValue == 0){
        wrongField(addOrEdit);
        alert("Please enter a serving amount");
        return true;
    }
 
    return false;
}

function checkLength(addOrEdit){
    let lengthValue = document.getElementById(addOrEdit).value;

    if (lengthValue == 0){
        wrongField(addOrEdit);
        alert("Please enter how long it took to make");
        return true;
    }
    if (lengthValue >600){
        wrongField(addOrEdit);
        alert("Please enter a time less than 600 minutes");
        return true;
    }
   
    return false;
}

function wrongField(whatIsWrong){
    document.getElementById(whatIsWrong).style.backgroundColor = "#FF8383";
}

function correctField(whatIsRight){
    document.getElementById(whatIsRight).style.backgroundColor = "white";
}
