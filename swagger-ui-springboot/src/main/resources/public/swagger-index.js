window.onload = function () {
    getLinks(generateSpecs);
}

function generateSpecs(responseText){
    const apis = createApiInfoList(responseText);
    const token = getToken();
    buildSystem(apis,token);

}
function getLinks(callback) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:6969/api-doc/links", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("receive the response:" + xhttp.responseText);
            callback(xhttp.responseText);
        }
    };
}
function buildSystem(apis,accessToken) {
    const ui = SwaggerUIBundle({
        urls: apis,
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        plugins: [
            SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout",
        requestInterceptor: function(request) {
            request.headers.Authorization = "Bearer " + accessToken;
            return request;
        }
    });
    window.ui = ui

}
function createApiInfoList(responseText){
    var apis= [];
    responseText.split(",").forEach(function (link) {
        const api = new APIInfo(link,/api-doc/+link);
        apis.push(api);
    })
    return apis;
}
function getToken(){
    return "TBD";
}
function APIInfo(name, url) {
    this.name = name;
    this.url = url;
}