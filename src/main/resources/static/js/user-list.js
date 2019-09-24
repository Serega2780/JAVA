$(document).ready(function () {
    userList();
});

function userList() {
    $.ajax({
        dataType: "json",
        type: 'GET',
        url: "/admin/users",

    }).done(function (data, textStatus, jqXHR) {
        console.error(data);
        $.each(data, function (i, item) {
            console.error(item.name);
            $('#userBody').append("<tr>")
                .append("<td>" + item.id + "</td>")
            $.each(item.grantedAuthorities,function (k, v) {
                $('#userBody').append(v.role)
            })

              //  .append("<td>" + "</td>")
                .append("<td>" + item.name + "</td>")
                .append("<td>" + item.password + "</td>")
                .append("<td>" + item.email + "</td>")
                .append("<td>" + item.country + "</td>")


                .append("</tr>");

        });
        //  location.reload();
    }).fail(function (jqXHR, textStatus, errorThrown) {

        console.error('Booh! Wrong credentials, try again!');
    });
}