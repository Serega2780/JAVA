$(document).ready(function () {
    userList();
  //  $('#myModal').modal('show');

    $('#userBody').on('click', '.item-edit', function(){
        var id = $(this).attr('data');
        $('#myModal').modal('show');
        $('#myModal').find('.modal-title').text('Edit user');
      //  $('#myform').attr('action','<?php echo base_url(); ?>Main_controller/updateData')
        $.ajax({
            type: 'ajax',
            method: 'GET',
            url: "/admin/edit",
            data:{id: id},
            async: false,
            dataType: 'json',
            success: function(data){
                $('input[name=id]').val(data.id);
                $('input[name=name]').val(data.name);
                $('input[name=password]').val(data.password);
                $('input[name=email]').val(data.email);
                $('#countryList').val(data.country);
            },
            error: function(){
                alert('could not Edit user');
            }
        });

    });
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
            var roles = "";
            var hrefEdit = "<a href=\"#\" class=\"btn btn-info item-edit\" data=\"" +
                +item.id+"\" data-toggle=\"modal\" data-target=\"#myModal0\" role=\"button\" >Edit</a>";

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

        });
        //  location.reload();
    }).fail(function (jqXHR, textStatus, errorThrown) {

        console.error('Booh! Wrong credentials, try again!');
    });
}