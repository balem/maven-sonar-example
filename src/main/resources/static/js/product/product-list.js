$(document).ready(function () {

    var table = $('#product-table').DataTable({
        "ajax": {
            "url": "/purchase/products/",
            "type": "get",
            "dataType": "json"
        },
        "columns": [
            {"data": "id"},
            {"data": "product_name"},
            {"data": "brand.description"},
            {"data": "category.description"},
            {
                "data": "action",
                "className": "center",
                "defaultContent": '<button class="btn btn-primary edit"><i class="fa fa-edit"></i></button> <button href="" class="btn btn-danger delete"><i class="fa fa-trash"></i></button>'
            }
        ]

    });



    $('body').on('click', '.edit', function(){
        var row  = $(this).parents('tr')[0];
        var data = table.row( row ).data();
        var editForm = "/purchase/products/form-edit?id="+data.id;
        window.location.href = editForm;
    });

});