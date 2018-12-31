function getRowInfo(url) {
    // var serverURL = "localhost:8080";

    var form = $("#show-form");

    $.get(url, function(data){
        var json = JSON.parse(data);
        var children = form.find("input");
        var child;
        for(var i=0; i<children.length; i++){
            // child is not the object of jQuery, so we could not use jQuery's method
            child = children[i];
            var attr = child.getAttribute("id");
            child.value = json.info[attr];
        }
    });
}

function warnError() {
    alert("不好意思出错了，请稍后再试~");
}