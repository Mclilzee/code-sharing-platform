const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
    const codeValues = e.target.elements;
    e.preventDefault();
    const json = JSON.stringify(getCodeObject(codeValues));
    console.log(codeValues);
    postJsonRequest(json);
});

function getCodeObject(values) {
    return {
        code: values.code.value,
        views: values.views.value,
        time: values.time.value
    }
}

function postJsonRequest(json) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/code/new", false);
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xhr.send(json);
}