/**
 * Created by 志鹏 on 2016/11/4.
 */

$(document).ready(function () {
    $("#query").on("click", function () {
        var stocknumber = $("#Stocknumber").val();
        var data = {"stocknumber": stocknumber}
        $.ajax({
                type: "POST",
                data: data,
                url: "querystock1",
                success: function (rsp) {
                   
                    if(rsp.msg=="0") {
                        $("#accounts").append("<tr>" +
                            "<td id='gid'>" + rsp.data.gid + "</td>" +
                            "<td id='name'>" + rsp.data.name + "</td>" +
                            "<td id='nowPri'>" + rsp.data.nowPri + "</td>" +
                            "<td id='yestodEndPri'>" + rsp.data.yestodEndPri + "</td>" +
                            "<td id='todayMax'>" + rsp.data.yestodEndPri + "</td>" +
                            "<td id='todayMin'>" + rsp.data.todayMin + "</td>" +
                            "<td id='traNumber'>" + rsp.data.traNumber + "</td>" +
                            "<td id='date'>" + rsp.data.date + "</td>" +
                            "</tr>"
                        )
                        /** $("#imgminurl").attr('src', rsp.data.minurl)
                        $("#imgdaynurl").attr('src', rsp.data.dayurl)
                        $("#imgweekurl").attr('src', rsp.data.weekurl)*/
                        $("#imgmonthurl").attr('src', rsp.data.monthurl)
                       
                    }
                    else {
                        if(rsp.msg=="202102"){
                            alert("查询不到该股票！请重新输入")
                        }
                        else if(rsp.msg=="202101"){
                            alert("参数错误!请重新输入")

                        }
                        else{
                            alert("未知错误"+rsp.msg)
                        }
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {

                    alert("失败" + XMLHttpRequest.readyState + "   " + textStatus + "  " + errorThrown)
                }
            }
        )
    })
})