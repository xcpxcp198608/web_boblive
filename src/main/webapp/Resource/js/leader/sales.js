/**
 * Created by patrick on 25/08/2017.
 */
$(function () {
    var oTable = $("#content_table").get(0);
    var oTbody = oTable.tBodies[0];
    var oSearch = $("#txSearch").get(0);

    oSearch.onkeyup = function () {
        if(oSearch.value === ""){
            for(var i= 0; i < oTbody.rows.length; i ++){
                oTbody.rows[i].style.display = "";
            }
        }else {
            for (var j = 0; j < oTbody.rows.length; j++) {
                for (var c = 0; c < 4; c++) {
                    var tValue = oTbody.rows[j].cells[c].innerHTML.toLowerCase();
                    var sValue = oSearch.value.toLowerCase();
                    if(tValue.search(sValue) >= 0){
                        oTbody.rows[j].style.display = "";
                        break;
                    }else{
                        oTbody.rows[j].style.display = "none";
                    }
                }
            }
        }
    };

/******************************************** activate or deactivate by sales *****************************************/
    var urlActivate = "http://"+location.host+"/boblive/auth1/activate";
    var urlDeactivate = "http://"+location.host+"/boblive/auth1/deactivate";
    for(var d = 0 ; d < oTbody.rows.length ; d ++){
        var oTdActivate = oTbody.rows[d].cells[6];
        oTdActivate.onclick = function () {
            var sales = this.parentNode.cells[1].children[0].innerHTML;
            activateOrDeactivate(urlActivate, sales)
        };
        var oTdDeactivate = oTbody.rows[d].cells[7];
        oTdDeactivate.onclick = function () {
            var sales = this.parentNode.cells[1].children[0].innerHTML;
            activateOrDeactivate(urlDeactivate , sales)
        };
    }

    function activateOrDeactivate(url, sales) {
        $.ajax({
            type: "POST",
            url: url,
            data: {"manager":"leader", "sales":sales},
            dataType: "json",
            beforeSend:function () {
                $("#noticeFragment").css("display","block");
                $("#notice_content").html("processing");
            },
            success: function (response) {
                $("#notice_content").html(response.message);
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },500);
            },
            complete:function () {

            },
            error:function () {

            }
        });
    }

/**************************************************** create sales ****************************************************/

    $("#add_sales").click(function () {
        $("#createFragment").css("display","block");
    });
    $(window).scroll(function () {
        $("#dCreate").css("top",window.scrollY + 150);
        $("#dUpdate").css("top",window.scrollY + 150);
    });
    $("#closeCreate").click(function () {
        $("#createFragment").css("display","none");
    });
    $("#iUserName").blur(function () {
        if($("#iUserName").val().length < 5){
            $("#createNotice").css("display","block");
            $("#createNotice").html("sales name length < 5");
        }else{
            $("#createNotice").css("display","none");
        }
    });
    $("#iPassword").blur(function () {
        if($("#iPassword").val().length < 6){
            $("#createNotice").css("display","block");
            $("#createNotice").html("password length < 6");
        }else{
            $("#createNotice").css("display","none");
        }
    });
    $("#sDealer").change(function () {
        var index = this.selectedIndex;
        if(index === 0){
            $("#btConfirm").css("disabled", "disabled");
            return;
        }
        $("#btConfirm").removeAttr("disabled");
    });
    $("#btConfirm").click(function () {
        var url = "http://"+location.host+"/boblive/sales/create";
        var userName = $("#iUserName").val();
        var password = $("#iPassword").val();
        var dealer = $("#sDealer").val();
        createSales(url, dealer, userName, password);
    });

    function createSales(url, dealer, userName, password) {
        $.ajax({
            type: "POST",
            url: url,
            data:{"dealer": dealer, "userName":userName, "password":password},
            dataType: "json",
            success: function (response) {
                if(response.code === 200){
                    var oTr = document.createElement("tr");
                    oTr.className = "tr1";
                    for(var key in response.data) {
                        if(key !== "leader") {
                            var oTd = document.createElement("td");
                            oTd.innerHTML = response.data[key];
                            oTd.setAttribute("align", "center");
                            oTr.appendChild(oTd);
                        }
                    }
                    oTbody.appendChild(oTr);
                    $("#createFragment").css("display","none");
                    $("#noticeFragment").css("display","block");
                    $("#notice_content").html(response.message);
                    setTimeout(function () {
                        $("#noticeFragment").css("display","none");
                    },1000);
                }else{
                    $("#createNotice").css("display","block");
                    $("#createNotice").html(response.message);
                }
            },
            error: function () {
                $("#createNotice").css("display","block");
                $("#createNotice").html("communication error");
            }
        })
    }

/************************************************** update password ***************************************************/

    for(var i = 0; i < oTbody.rows.length; i ++){
        var oUpdate = oTbody.rows[i].cells[8];
        oUpdate.onclick = function () {
            var userName = this.parentNode.cells[1].children[0].innerHTML;
            var password = this.parentNode.cells[2].children[0].value;
            updatePassword(userName, password);
        }
    }
    var url_update = "http://"+location.host+"/boblive/sales/update";
    function updatePassword(userName, password) {
        $.ajax({
            url:url_update,
            method: "POST",
            data: {"userName":userName, "password":password},
            dataType: "json",
            beforeSend: function () {
                $("#noticeFragment").css("display","block");
                $("#notice_content").html("processing");
            },
            success: function (response) {
                $("#notice_content").html(response.message);
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },500);
            },
            error: function () {
                $("#notice_content").html("communication error");
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },500);
            }
        })
    }

});




function updatePassword(obj) {
    var password = prompt("type in password");
    if(textIsEmpty(password)){
        alert("password format error");
        return false
    }
    obj.href += password
}