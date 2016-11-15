/**
 * Created by 志鹏 on 2016/10/20.
 */
$(document).ready(function () {
    $("#Popup").hide();

    $("#register").on("click", function () {

        var userName = $("#username").val();
        var userPsd = $("#password1").val();
        var configerpassWord = $("#password2").val();

        if (userPsd == configerpassWord) {
            $("#Popup").hide();

            var date = {"userName": userName, "userPsd": userPsd}

            $.ajax({
                type: "POST",
                data: date,
                url: "register1",
                success: function (rsp) {
                    if (rsp.success == true) {
                        alert("用户已存在");
                    }
                    else if (rsp.success == false) {
                        alert("用户注册成功")
                    } else {
                        alert("unknow mistake");
                    }
                },
                error: function (rsp) {

                    alert("数据发送失败");
                }
            });
        } else {
            $("#Popup").show();
        }
    })


    $("#tologin").on("click", function () {
        location.href = "http://r159398a65.imwork.net/login";
    })
})