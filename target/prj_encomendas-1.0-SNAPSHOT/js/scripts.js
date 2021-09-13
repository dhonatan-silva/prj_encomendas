
//Método que fecha a mensagem
function closeMessage() {
    document.querySelector("#formMessage > table").style.display = "none";
}


function onlyNumber(e) {
    var number = e.key;
    var regex = new RegExp("^[0-9]+$");
    return regex.test(number);
}

function onlyLetter(e) {
    var letter = e.key;
    var regex = new RegExp("[a-zA-Z]");
    return regex.test(letter);
}

function validateEmail(obj) {
    var regex = new RegExp(/\S+@\S+\.\S+/);
    var valid = regex.test(obj.value);
    if (valid) {
        obj.classList.remove("is-invalid");
        obj.classList.add("is-valid");
    } else {
        obj.classList.remove("is-valid");
        obj.classList.add("is-invalid");
    }
}

$(document).ready(function () {
    $(".toUpperCase").css("text-transform", "uppercase");
    $(".toUpperCase").keyup(function () {
        $(this).val($(this).val().toUpperCase());
    });
});

$(document).ready(function () {
    $(".toLowerCase").css("text-transform", "lowercase");
    $(".toLowerCase").keyup(function () {
        $(this).val($(this).val().toLowerCase());
    });
});

