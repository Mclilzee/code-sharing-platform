const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
    const codeValue = e.target.elements.code.value;
    e.preventDefault();
    const json = JSON.stringify(getCodeObject(codeValue));
    postJsonRequest(json);
});

function getCodeObject(value) {
    return {
        code: value
    }
}

function postJsonRequest(json) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/code/new", false);
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xhr.send(json);

    if (xhr.status == 200) {
        alert("success!");
    }
}