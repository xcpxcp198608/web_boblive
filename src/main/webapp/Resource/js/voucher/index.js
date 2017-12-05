$(function () {

    var baseUrl = "http://"+location.host+"/boblive/voucher/";
    var jLoading = $('#loading');

    $('#btSignIn').click(function(){
        var jErrorMessage = $('#error_message_sign_in');
        var username = $('#sign_in_username').val();
        var password = $('#sign_in_password').val();
        if(username.length <= 0){
            jErrorMessage.html('username input error');
            return
        }
        if(password.length <= 0){
            jErrorMessage.html('password input error');
            return
        }
        jErrorMessage.html(' ');
        jErrorMessage.css('display', 'none');
        $.ajax({
            type: "GET",
            url: baseUrl + "signin",
            data: {"username": username, "password": password},
            dataType: "json",
            beforeSend:function () {
                jLoading.css('display', 'block');
            },
            success: function (response) {
                if(response.code === 200){
                    window.open(baseUrl +"order/", "_self")
                }else{
                    jLoading.css('display', 'none');
                    jErrorMessage.html(response.message);
                    jErrorMessage.css('display', 'block');
                }
            },
            error:function (error) {
                jLoading.css('display', 'none');
                console.log(error);
            }
        })
    });

});