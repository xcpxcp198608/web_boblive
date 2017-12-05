/**
 * Created by patrick on 25/08/2017.
 */
$(function () {
    var oTable = $("#content_table").get(0);
    var oTbody = oTable.tBodies[0];
    var oSearch = $("#txSearch").get(0);
    var totalCount = $("#totalCount").get(0);
    showTotalCount();

/************************************************* ShowTotalCount *****************************************************/
    function showTotalCount() {
        var count = 0;
        for(var x =0 ; x < oTbody.rows.length; x ++){
            var status = oTbody.rows[x].style.display;
            if(status !== 'none'){
                count ++;
            }
        }
        totalCount.innerHTML = ""+count
    }

/******************************************************* search *******************************************************/
    oSearch.onkeyup = function () {
        if(oSearch.value == ""){
            for(var i =0 ; i < oTbody.rows.length; i ++){
                oTbody.rows[i].style.display = "";
            }
        }else{
            for(var j =0 ; j < oTbody.rows.length; j ++){
                for(var c =0 ; c < 8; c ++) {
                    if (c != 6) {
                        var tValue = oTbody.rows[j].cells[c].innerHTML.toLowerCase();
                        var sValue = oSearch.value.toLowerCase();
                        if (tValue.search(sValue) >= 0) {
                            oTbody.rows[j].style.display = "";
                            break;
                        } else {
                            oTbody.rows[j].style.display = "none";
                        }
                    }
                }
            }
        }
        showTotalCount();
    };

/************************************************* sort by level ******************************************************/
    $("#sLevel").change(function () {
        for(var n =0 ; n < oTbody.rows.length; n ++){
            if(oTbody.rows[n].cells[5].innerHTML == $("#sLevel").val() || $("#sLevel").val() == 0 ){
                oTbody.rows[n].style.display = "";
            }else{
                oTbody.rows[n].style.display = "none";
            }
        }
        showTotalCount();
    });


/************************************************* sort by online *****************************************************/
    $("#sOnline").change(function () {
        for(var n =0 ; n < oTbody.rows.length; n ++){
            if(oTbody.rows[n].cells[10].children[0].getAttribute("alt") == $("#sOnline").val() || $("#sOnline").val() ==0 ){
                oTbody.rows[n].style.display = "";
            }else{
                oTbody.rows[n].style.display = "none";
            }
        }
        showTotalCount();
    });

/************************************************* sort by status *****************************************************/
    $("#sStatus").change(function () {
        for(var n =0 ; n < oTbody.rows.length; n ++){
            if(oTbody.rows[n].cells[11].children[0].getAttribute("alt") == $("#sStatus").val() || $("#sStatus").val() == 0){
                oTbody.rows[n].style.display = "";
            }else{
                oTbody.rows[n].style.display = "none";
            }
        }
        showTotalCount();
    });
    
/**************************************************** dealer selected *************************************************/
    $("#dealerSelect").change(function () {
        var index = this.selectedIndex;
        if(index == 0){
            $("#salesSelect")[0].options.length = 0;
            var oOption = document.createElement("option");
            oOption.value = "";
            oOption.text = "-- sales --";
            $("#salesSelect")[0].add(oOption, null);
            return;
        }
        var dealer = this.value;
        $("#salesSelect")[0].options.length = 0;
        var url = "http://"+location.host+"/boblive/sales/";
        $.ajax({
            type:"POST",
            url: url,
            data: {"dealer": dealer},
            dataType: "json",
            success: function (data) {
                var jsonArray = eval(data);
                var oSalesSelect = $g("salesSelect");
                for (var i =0 ; i < jsonArray.length ; i ++){
                    var sales = jsonArray[i].userName;
                    var oOption = document.createElement("option");
                    oOption.value = sales;
                    oOption.text = sales;
                    oSalesSelect.add(oOption, null);
                }
            }
        })
    });

/*************************************************** update operation *************************************************/

    var urlActivate = "http://"+location.host+"/boblive/auth1/activate";
    var urlDeactivate = "http://"+location.host+"/boblive/auth1/deactivate";
    var urlUpdate = "http://"+location.host+"/boblive/auth1/update";
    var urlTemp = "http://"+location.host+"/boblive/auth1/updateTemporary";
    if(oTbody.rows.length > 0) {
        for (var d = 0; d < oTbody.rows.length; d++) {
            var oTdActivate = oTbody.rows[d].cells[12];
            oTdActivate.onclick = function () {
                var key = this.parentNode.cells[1].innerHTML;
                var oStatus = this.parentNode.cells[11].children[0];
                activateOrDeactivate(urlActivate, key, oStatus)
            };
            var oTdDeactivate = oTbody.rows[d].cells[13];
            oTdDeactivate.onclick = function () {
                var key = this.parentNode.cells[1].innerHTML;
                var oStatus = this.parentNode.cells[11].children[0];
                activateOrDeactivate(urlDeactivate, key, oStatus)
            };
            var oUpdate = oTbody.rows[d].cells[8].children[3];
            oUpdate.onclick = function () {
                var id = this.parentNode.children[0].value;
                var level = this.parentNode.children[1].value;
                var days = this.parentNode.children[2].value;
                updateLevel(this.parentNode.parentNode, id, level, days)
            };
            var oLabel = oTbody.rows[d].cells[9].children[0].children[1];
            oLabel.onclick = function () {
                var oTr = this.parentNode.parentNode.parentNode;
                var temp = this.parentNode.children[0].checked ? 1 : 0;
                temp = temp == 0 ? 1 : 0;
                var key = this.parentNode.parentNode.parentNode.children[1].innerHTML;
                updateTemp(oTr, key, temp);
            };
        }
    }

    function activateOrDeactivate(url, key ,oStatus) {
        $.ajax({
            type: "POST",
            url: url,
            data: {"key":key},
            dataType: "json",
            beforeSend:function () {
                $("#noticeFragment").css("display","block");
                $("#notice_content").html("processing");
            },
            success: function (response) {
                var data = eval(response);
                $("#notice_content").html(data.message);
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },500);
                if(data.code == 202){
                    oStatus.src = "Resource/img/blue.png";
                }else if(data.code == 203){
                    oStatus.src = "Resource/img/red.png";
                }
            },
            error:function () {
                $("#notice_content").html("communication error");
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },500);
            }
        });
    }

    function updateLevel(oTr, id, level, days) {
        $.ajax({
            url: urlUpdate,
            method: "POST",
            data: {"id": id, "level": level, "days": days},
            dataType: "json",
            beforeSend: function () {
                $("#noticeFragment").css("display","block");
                $("#notice_content").html("processing");
            },
            success: function (response) {
                var json = eval(response);
                oTr.cells[5].innerHTML = json.obj.level;
                oTr.cells[7].innerHTML = json.obj.memberDate;
                $("#notice_content").html(json.message);
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
        });
    }

    function updateTemp(oTr, key, temp) {
        $.ajax({
            url: urlTemp,
            method: "POST",
            data: {"key":key, "temporary":temp},
            dataType: "json",
            beforeSend: function () {
                $("#noticeFragment").css("display","block");
                $("#notice_content").html("processing");
            },
            success: function (response) {
                var json = eval(response);
                if(json.code = 200) {
                    if (json.obj.temporary) {
                        oTr.cells[9].children[0].children[0].checked = true;
                    }else {
                        oTr.cells[9].children[0].children[0].checked = false;
                    }
                }else{
                    oTr.cells[9].children[0].children[0].checked = false;
                }
                $("#notice_content").html(json.message);
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },500);
            },
            error: function () {
                oTr.cells[9].children[0].children[0].checked = false;
                $("#notice_content").html("communication error");
                setTimeout(function () {
                    $("#noticeFragment").css("display","none");
                },500);
            }
        });
    }
    
    
});
