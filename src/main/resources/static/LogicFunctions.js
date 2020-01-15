

function checkRecipeDetails(){

    correctField("recipeName");
    correctField("serving");
    correctField("howLong");

    if (checkRecipeName()){
        return false;
    }
    else if (checkServing()){
        return false;
    }
    else if (checkLength()){
        return false;
    }
    else{
        addRecipe();
    }

}

function checkRecipeName(){
    let recipeValue = document.getElementById("recipeName").value;
    var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
    var numChars = "0123456789";
    var alphaChars = "abcdefghijklmnopqrstuvwxyz";

    if (recipeValue.length <3){
        wrongField("recipeName");
        alert("Please enter a name longer than 2 characters");
        return true;
    }
    if (recipeValue.length >50){
        wrongField("recipeName");
        alert("Please enter a name shorter than 50 characters");
        return true;
    }
    
    for (var i = 0; i < recipeValue.length; i++) {
    if (iChars.indexOf(recipeValue.charAt(i)) != -1) {
        wrongField("recipeName");
        alert ("Your recipe cannot contain special characters");
        return true;
    }
    for (var i = 0; i < recipeValue.length; i++) {
        if (iChars.indexOf(recipeValue.charAt(i)) != -1) {
            wrongField("recipeName");
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
                    wrongField("recipeName");
                    alert ("Your recipe cannot contain only numbers");
                    return true;
                
            }
        }
    }

    return false;
}





function checkServing(){
    let servingValue = document.getElementById('serving').value;

    if (servingValue == 0){
        wrongField('serving');
        alert("Please enter a serving amount");
        return true;
    }
 
    return false;
}

function checkLength(){
    let lengthValue = document.getElementById('howLong').value;

    if (lengthValue == 0){
        wrongField('howLong');
        alert("Please enter how long it took to make");
        return true;
    }
    if (lengthValue >600){
        wrongField('howLong');
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
