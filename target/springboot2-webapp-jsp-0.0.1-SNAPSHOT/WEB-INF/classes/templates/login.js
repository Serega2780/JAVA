jQuery(document).ready(function ($) {
    $('#LoginForm').submit(function (event) {
        event.preventDefault();
        var data = 'login=' + $('#login').val() + '&password=' + $('#password').val();
        $.ajax({
            data: data,
            timeout: 1000,
            type: 'POST',
            url: '/perform_login'

        }).done(function(data, textStatus, jqXHR) {
            showMeYourJqXHR('When loginform is done', jqXHR);
            showMeYourCookies('When loginform is done');

            window.location = cookie.url;

        }).fail(function(jqXHR, textStatus, errorThrown) {
            showMeYourJqXHR('When loginform fails', jqXHR);
            showMeYourCookies('When loginform fails');

            console.error('Booh! Wrong credentials, try again!');
        });
    });
});