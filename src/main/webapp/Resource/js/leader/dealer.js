/**
 * Created by xuchengpeng on 24/08/2017.
 */
$(function () {

    var oTxSearch = $("#txSearch").get(0);
    var oTable = $("#content_table").get(0);
    var oTbody = oTable.tBodies[0];

/********************************************************* search *****************************************************/

    oTxSearch.onkeyup = function () {
        if(oTxSearch.value == ""){
            for(var i= 0 ; i < oTbody.rows.length; i ++){
                oTbody.rows[i].style.display = "";
            }
        }else{
            for(var j= 0 ; j < oTbody.rows.length; j ++){
                for(var c= 0 ; c < 3; c ++){
                    var tValue = oTbody.rows[j].cells[c].innerHTML.toLowerCase();
                    var tSearch = oTxSearch.value.toLowerCase();
                    if(tValue.search(tSearch) >= 0){
                        oTbody.rows[j].style.display = "";
                        break;
                    }else{
                        oTbody.rows[j].style.display = "none";
                    }

                }

            }
        }
    };

/******************************************************* sort table ***************************************************/

    function sortTable(n , flag) {
        var arr = [];
        for(var i = 0 ; i <oTbody.rows.length; i ++){
            arr[i] = oTbody.rows[i];
        }
        arr.sort(function (tr1, tr2) {
            if(n == 0) {
                return parseInt(tr1.cells[n].innerHTML) - parseInt(tr2.cells[n].innerHTML);
            }else{
                return tr1.cells[n].innerHTML.toLowerCase() < tr2.cells[n].innerHTML.toLowerCase();
            }
        });
        if(flag) {
            for (var j = arr.length - 1; j >= 0; j--) {
                oTbody.appendChild(arr[j])
            }
            flag = !flag
        }else{
            for (var k = 0; k < arr.length; k++) {
                oTbody.appendChild(arr[k])
            }
            flag = !flag
        }
    }

    var oThId = oTable.tHead.rows[0].cells[0];
    var idFlag = true;
    var userNameFlag = true;
    var activeFlag = true;
    var updateFlag = true;
    oThId.onclick = function(){sortTable(0, idFlag)};
    // var oThUserName = oTable.tHead.rows[0].cells[1];
    // oThUserName.onclick = function(){sortTable(1, userNameFlag)};
    // var oThActiveDate = oTable.tHead.rows[0].cells[3];
    // oThActiveDate.onclick = function(){sortTable(3, activeFlag)};
    // var oThUpdateDate = oTable.tHead.rows[0].cells[4];
    // oThUpdateDate.onclick = function(){sortTable(4, updateFlag)};

/******************************************* activate or deactivate by dealer *****************************************/

    var urlActivate = "http://"+location.host+"/boblive/auth1/activate";
    var urlDeactivate = "http://"+location.host+"/boblive/auth1/deactivate";
    for(var d = 0 ; d < oTbody.rows.length ; d ++){
        var oTdActivate = oTbody.rows[d].cells[5];
        oTdActivate.onclick = function () {
            var dealer = this.parentNode.cells[1].children[0].innerHTML;
            activateOrDeactivate(urlActivate, dealer)
        };
        var oTdDeactivate = oTbody.rows[d].cells[6];
        oTdDeactivate.onclick = function () {
            var dealer = this.parentNode.cells[1].children[0].innerHTML;
            activateOrDeactivate(urlDeactivate , dealer)
        };
    }

    function activateOrDeactivate(url, paramValue) {
        $.ajax({
            type: "POST",
            url: url,
            data: {"manager":"leader", "dealer":paramValue},
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

/******************************************************** create dealer ***********************************************/

    var oAddDealer = $("#add_dealer").get(0);
    var oPopFragment = $("#createFragment").get(0);
    var oCloseCreate = $("#closeCreate").get(0);
    var oBtConfirm = $("#btConfirm").get(0);
    var oCreateNotice = $("#createNotice").get(0);
    oAddDealer.onclick = function () {
        oPopFragment.style.display = "block";
    };
    oCloseCreate.onclick = function () {
        oPopFragment.style.display = "none";
    };
    $("#iUserName").blur(function () {
        if($("#iUserName").val().length < 5){
            oCreateNotice.innerHTML = "dealer name length < 5";
            oCreateNotice.style.display = "block";
        }else{
            oCreateNotice.style.display = "none";
        }
    });
    $("#iPassword").blur(function () {
        if($("#iPassword").val().length < 6){
            oCreateNotice.style.display = "block";
            oCreateNotice.innerHTML = "password length < 6";
            oBtConfirm.setAttribute("disabled", "disabled");
        }else{
            oCreateNotice.style.display = "none";
            oBtConfirm.removeAttribute("disabled");
        }
    });

    oBtConfirm.onclick = function () {
        var url = "http://"+location.host+"/boblive/dealer/create";
        var userName = $("#iUserName").val();
        var password = $("#iPassword").val();
        createDealer(url, userName, password);
    };

    function createDealer(url, userName, password) {
        $.ajax({
            type: "POST",
            url: url,
            data:{"userName":userName, "password":password},
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

                    oPopFragment.style.display = "none";
                    $("#noticeFragment").css("display","block");
                    $("#notice_content").html(json.message);
                    setTimeout(function () {
                        $("#noticeFragment").css("display","none");
                    },1000);
                }else{
                    oCreateNotice.style.display = "block";
                    oCreateNotice.innerHTML = json.message;
                }
            },
            error: function () {
                oCreateNotice.style.display = "block";
                oCreateNotice.innerHTML = "request error";
            }
        })
    }

/**************************************** create fragment follow window scroll*****************************************/
    $(window).scroll(function () {
        $("#dCreate").css("top",window.scrollY + 150);
        $("#dUpdate").css("top",window.scrollY + 150);
    });


/*********************************************** update password fragment *********************************************/
    var updateUrl = "http://"+location.host+"/boblive/dealer/update";
    for(var i = 0; i < oTbody.rows.length; i ++) {
        var oUpdate = oTbody.rows[i].cells[7];
        oUpdate.onclick = function () {
            var oPassword = this.parentNode.cells[2].children[0];
            var dealer = this.parentNode.cells[1].children[0].innerHTML;
            var password = oPassword.value;
            updatePassword(oPassword, dealer, password);
        }
    }

    function updatePassword(oPassword, dealer, password) {
        $.ajax({
            url: updateUrl,
            method: "POST",
            data:{"userName":dealer, "password":password},
            dataType:"json",
            beforeSend:function () {
                $("#noticeFragment").css("display","block");
                $("#notice_content").html("processing");
            },
            success:function (response) {
                if(response.code === 200){
                    oPassword.setAttribute("value",password);
                }
                $("#noticeFragment").css("display","block");
                $("#notice_content").html(data.message);
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },1000);
            },
            error:function () {
                $("#noticeFragment").css("display","block");
                $("#notice_content").html("server error");
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },1000);
            }
        })
    }



});


