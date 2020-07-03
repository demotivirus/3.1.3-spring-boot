function deleteUser(id) {
    var res = confirm('Confirm delete');
    if (res) {
        $.ajax({
            url: '/api/users/' + id,
            method: 'DELETE',
            success: function () {
                getAllUsers();
            },
            error: function (error) {
                alert(error);
            }
        })
    }
}