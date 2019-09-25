$(document).ready(function () {
    userList();

    //press Edit button (open Modal window)
    $('#userBody').on('click', '.item-edit', function () {
        var id = $(this).attr('data');
        $('#myModal').modal('show');
        $('#myModal').find('.modal-title').text('Edit user');
        $.ajax({
            type: 'ajax',
            method: 'GET',
            url: "/admin/edit",
            data: {id: id},
            async: false,
            dataType: 'json',
            success: function (data) {
                console.error(data);
                $('input[name=id]').val(data.id);
                $('input[name=name]').val(data.name);
                $('input[name=password]').val(data.password);
                $('input[name=email]').val(data.email);
                $('#countryList').append('<option selected="selected" value="' +data.country + '\">' + data.country + '</option>');
                data.grantedAuthorities.forEach(function (item) {
                    $('#roles').append('<option style="font-weight: bold;" selected="selected" value="' +item.authority + '\">' + item.authority + '</option>');

                });
            },
            error: function () {
                alert('could not Edit user');
            }
        });

        $.ajax({
            type: 'ajax',
            method: 'GET',
            url: "/admin/countries",
            async: false,
            dataType: 'json',
            success: function (countries) {
                countries.forEach(function (item) {
                    $('#countryList').append('<option value="' +item + '\">' + item + '</option>');

                });

            },
            error: function () {
                alert('could not Edit user');
            }
        });

        $.ajax({
            method: 'GET',
            url: "/admin/roles",
            async: false,
            dataType: 'json',
        }).done(function (roles) {
            $('#roles').append('<option value=""> --</option>');
            $.each(roles, function (i, role) {
                $('#roles').append('<option value="' +role.role + '\">' + role.role + '</option>');
            })



        }).fail(function (jqXHR, textStatus, errorThrown) {

            console.error('Booh! Wrong rles, try again!');
        });


    });


    //Select roles

//Save Edit User
    $('#modalSave').click(function () {
        var roles="";
        $('#roles option:selected').each(function() {
            roles+=$(this).val()+', ';
        });

        var form = $(this);
        var id = $('input[name=id]').val();
        var name = $('input[name=name]').val();
        var password = $('input[name=password]').val();
        var email = $('input[name=email]').val();
        var country = $('#countryList option:selected').val();
      //  alert(country);
        var roles2 = $('#roles').val();
        var rls=roles2.toString().split(",");
        alert(rls[1]);
        var str = "{id: id, name: name, password: password, email: email, country: country}";
        var user = JSON.stringify(str);
        alert(user);
        $.ajax({
            method: 'PUT',
            url: "/admin/insert",
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            data: user,
        }).done(function (roles) {
            console.error(roles);
        }).fail(function () {

            console.error(roles);
        });
    })


//Clear countries/roles list
    $('#modalClose').click(function () {
        $('#countryList')
            .find('option')
            .remove()
            .end()
        ;

        $('#roles')
            .find('option')
            .remove()
            .end()
        ;
    })

    function userList() {
        $.ajax({
            dataType: "json",
            type: 'GET',
            url: "/admin/users",

        }).done(function (data, textStatus, jqXHR) {
            // console.error(data);
            $.each(data, function (i, item) {
                console.error(item.name);
                var roles = "";
                var hrefEdit = "<a href=\"#\" class=\"btn btn-info item-edit\" data=\"" +
                    +item.id + "\" data-toggle=\"modal\" data-target=\"#myModal0\" role=\"button\" >Edit</a>";

                var hrefDelete = "<a href=\"delete?id=" + item.id + "\" class=\"btn btn-info item-delete\" role=\"button\" " +
                    "id=\"" + item.id + "\">Delete</a>";

                $.each(item.grantedAuthorities, function (k, v) {
                    roles += v.role + '\n';
                    //  alert(roles);
                })
                $('#userBody').append("<tr>")
                    .append("<td>" + item.id + "</td>")
                    .append("<td>" + roles + "</td>")
                    .append("<td>" + item.name + "</td>")
                    .append("<td>" + item.password + "</td>")
                    .append("<td>" + item.email + "</td>")
                    .append("<td>" + item.country + "</td>")

                    .append("<td>" + hrefEdit + "</td>")
                    .append("<td>" + hrefDelete + "</td>")
                    .append("</tr>");

            })
        }).fail(function (jqXHR, textStatus, errorThrown) {

            console.error('Booh! Wrong credentials, try again!');
        });
    }
})