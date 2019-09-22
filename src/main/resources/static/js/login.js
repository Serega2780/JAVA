jQuery(document).ready(function ($) {
    $('#LoginForm').submit(function (event) {
        event.preventDefault();
        var data = 'login=' + $('#login').val() + '&password=' + $('#password').val();
        $.ajax({
            data: data,
            timeout: 1000,
            type: 'POST',
            url: '/perform_login',

        }).done(function (data, textStatus, jqXHR) {
            location.reload();
        }).fail(function (jqXHR, textStatus, errorThrown) {

            console.error('Booh! Wrong credentials, try again!');
        });
    });
});