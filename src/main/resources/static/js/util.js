$(document).ready(function () {
    $('.sidebar-menu').tree()
    $.get("/menu/user", function (data) {
        $("#onmenuname, .nameonheader").text(data.name+" "+data.lastName);
        $(".rolonheader").text(data.rol);
    });

});