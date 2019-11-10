$(document).ready(function() {
    var title = $("<li>",{
        class: "header",
        text: "Menú principal"
    });

    var sidebarMenu = $("#sidebar-menu");
    sidebarMenu.append(title);

    $.ajax({
        url: "http://localhost:8081/menu"
    }).then(function(data) {

        $.each(data, function (index, section) {
            var menuSection = createMenu(section);
            var listUlMenu = $("<ul>",{class: "treeview-menu"});
            $.each(section.menues, function (j, menu) {
                var itemMenu = createItemMenu(menu);
                listUlMenu.append(itemMenu);
            });
            menuSection.append(listUlMenu);
            sidebarMenu.append(menuSection);
        });
    });


});

function createMenu(section) {
    var menuSection = $("<li>",{id: section.name,class: 'treeview'});
    var link = $("<a>",{href: '#',}
    ).append($("<i>",{class: section.icon})
    ).append($("<span>",{text: section.name})
    ).append($("<span>",{class: "pull-right-container"}
    ).append($("<i>",{ class: "fa fa-angle-left pull-right"}
    )));
    menuSection.append(link);
    return menuSection;
}

function createItemMenu(itemMenu) {
    return  $("<li>").append($("<a>",{href: itemMenu.url,text: itemMenu.name,id: itemMenu.id}
    ));
}