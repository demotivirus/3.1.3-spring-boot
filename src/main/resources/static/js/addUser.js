$(document).ready(function () {
    $('#addUserButton').click(function () {
        ajaxPost();

    });

    function ajaxPost() {
        var roleValue = "";
        var role = document.getElementById("authUserRole");
        if (role[role.selectedIndex].value === 1) {
            roleValue = "ADMIN";
        } else {
            roleValue = "USER";
        }

        var formData = {
            id: $("#addId").val(),
            name: $("#addName").val(),
            login: $("#addLogin").val(),
            password: $("#addPassword").val(),
            roles: [{
                id: role[role.selectedIndex].value,
                role: roleValue,
                authority: roleValue
            }
            ]
        };

        $.ajax({
            type: "POST",
            contentType: "application/json;",
            url: "/api/user",
            data: JSON.stringify(formData),
            dataType: 'json',
            complete: [
                function () {
                    getAllUsers();
                    $(document).ready(function () {
                        $("#usersTableTab").tab('show');
                        reset();
                    });
                    function reset(){
                        $('#addId').val('');
                        $('#addName').val('');
                        $('#addLogin').val('');
                        $('#addPassword').val('');
                    }
                }
            ]
        });
    }
});