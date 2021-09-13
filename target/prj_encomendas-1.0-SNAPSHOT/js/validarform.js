/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Validator {
    constructor(element, name, args, message, valid) {
        this.element = element;
        this.name = name;
        this.args = args;
        this.message = message;
        this.valid = valid;
    }
}

function generateValidator(validators, callBack) {
    let executes = [];
    let lasts = [];
    let events = ["blur", "keyup", "focus"];
    validators.forEach(function (validator) {
        events.forEach(function (evt) {
            validator.element.addEventListener(evt, function () {
                let resp = executarFunc(validator);
                executes.push(resp);

                for (let i = 1; i <= validators.length; i++) {
                    lasts.push(executes[executes.length - i]);
                }

                lasts.sort(function (a) {
                    if (a.valid === false) {
                        return 1;
                    }
                    if (a.valid === true) {
                        return -1;
                    }
                    return 0;
                });

                lasts.forEach(function (x) {
                    validate(x);
                });

                lasts = [];

                let result = verifyForm(validators);

                callBack(result);

            });
        });
    });
}

function  verifyForm(validators) {
    console.log(validators);
    let valid = true;
    validators.forEach(function (x) {
        if (x.valid === false) {
            valid = false;
        }
    });
    return valid;
}

function executarFunc(validator) {
    let params = [];
    params.push(validator.element.value);
    if (validator.args) {
        params.push(validator.args);
    }
    validator.valid = executeFunctionByName(validator.name, window, params);
    return validator;

}

function validate(validator) {
    if (validator) {
        var msg = validator.element.parentNode.querySelector(".invalid-feedback");
        if (validator.valid) {
            if (msg) {
                msg.parentNode.removeChild(msg);
            }
            validator.element.classList.remove("is-invalid");
            validator.element.classList.add("is-valid");
        } else {
            if (msg) {
                msg.parentNode.removeChild(msg);
            }
            validator.element.classList.remove("is-valid");
            validator.element.classList.add("is-invalid");
            validator.element.insertAdjacentHTML("afterend", "<div class='invalid-feedback'>" + validator.message + "</div>");
        }
    }
}

function executeFunctionByName(functionName, context, params) {
    return context[functionName].apply(context, params);
}

function minlength(value, min) {
    return (value.length > 0 && value.length >= min);
}

function maxlength(value, max) {
    return (value.length > 0 && value.length <= max);
}

function required(value) {
    return value.length > 0;
}

function startBlankSpace(value) {
    return !value.startsWith(" ");
}

function endBlankSpace(value) {
    return !value.endsWith(" ");
}

function moreThanName(value) {
    let names = value.split(" ");
    return names.length > 1;
}

function validEmail(value) {
    const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(String(value).toLowerCase());
}


