



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
        method : document.getElementById('method').value
    };
    console.log(newRecipe);
    JSON.stringify(newRecipe);
    axios.post('http://localhost:8080/createRecipe', newRecipe)
    .then(function(data) {
        window.alert("You have added " + document.getElementById('recipeName').value + " to your store!");
        window.location = "/RecipeViewAllPage.html";
    })
    .catch(function (error) {
        console.log(error);
    }); 
}
