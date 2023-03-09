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
    fetch("/api/code/new", {
    method: 'post',
    body: json,
    mode: 'cors',
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  })
  .then(response => response.json())
  .then(json => alert("ID: " + json.id))
}
