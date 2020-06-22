// Add the following code if you want the name of the file appear on select (Select avatar in register)
$(".custom-file-input").on("change", function() {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});

$(document).ready(function () {
    $imgSrc = $('#imgProfile').attr('src');
    function readURL(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#imgProfile').attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
    $('#btnChangePicture').on('click', function () {
        // document.getElementById('profilePicture').click();
        if (!$('#btnChangePicture').hasClass('changing')) {
            $('#profilePicture').click();
        }
        else {
            // change
        }
    });
    $('#profilePicture').on('change', function () {
        readURL(this);
        $('#btnChangePicture').addClass('changing');
        $('#btnChangePicture').attr('value', 'Confirm');
        $('#btnChangePicture').attr('type', 'submit');
        $('#btnDiscard').removeClass('d-none');
        // $('#imgProfile').attr('src', '');
    });
    $('#btnDiscard').on('click', function () {
        // if ($('#btnDiscard').hasClass('d-none')) {
        $('#btnChangePicture').removeClass('changing');
        $('#btnChangePicture').attr('value', 'Change');
        $('#btnChangePicture').attr('type', 'button');
        $('#btnDiscard').addClass('d-none');
        $('#imgProfile').attr('src', $imgSrc);
        $('#profilePicture').val('');
        // }
    });
    $('#historyOrder').DataTable();
    $('#processingOrder').DataTable();
});

$(function() {
    $('a.showmore').click(function(e) {
        e.preventDefault();
        // We break and store the result so we can use it to hide
        // the row after the slideToggle is closed
        const targetrow = $(this).closest('tr').next('.detail');
        targetrow.show().find('div').slideToggle('fast', function(){
            if (!$(this).is(':visible')) {
                targetrow.hide();
            }
        });
    });
});
