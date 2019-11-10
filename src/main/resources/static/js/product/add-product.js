function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            if($('#imagePreview img').length){
                $('#imagePreview img').attr("src", e.target.result);
            }else{
                $('#imagePreview').append($("<img>",{
                    src: e.target.result,
                    class: "img-responsive pad"
                }));
            }

        }

        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgupload").change(function() {
    readURL(this);
});