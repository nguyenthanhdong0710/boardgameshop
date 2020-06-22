// Add the following code if you want the name of the file appear on select (Select avatar in register)
$(".custom-file-input").on("change", function() {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});


$(document).ready(function() {
    $('#datatable').DataTable();
} );
