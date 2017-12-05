/**
 * Created by patrick on 25/08/2017.
 */
$(function () {
    var oTable = $("#content_table").get(0);
    var oTbody = oTable.tBodies[0];
    var oSearch = $("#txSearch").get(0);

/******************************************************** search ******************************************************/
    oSearch.onkeyup = function () {
        if(oSearch.value == ""){
            for(var i =0 ; i < oTbody.rows.length; i ++){
                oTbody.rows[i].style.display = "";
            }
        }else{
            for(var j =0 ; j < oTbody.rows.length; j ++){
                for(var c =0 ; c < 8; c ++) {
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

/****************************************************** update level **************************************************/
    for(var i =0; i < oTbody.rows.length; i++){
        var oUpdate = oTbody.rows[i].cells[7].children[3];
        oUpdate.onclick = function () {
            var id = this.parentNode.children[0].value;
            var level = this.parentNode.children[1].value;
            var days = this.parentNode.children[2].value;
            updateLevel(this.parentNode.parentNode, id, level, days)
        }
    }

    var url_update = "http://"+location.host+"/boblive/auth1/update";
    function updateLevel(oTr, id, level, days) {
        $.ajax({
            url: url_update,
            method: "POST",
            data: {"id": id, "level": level, "days": days},
            dataType: "json",
            beforeSend: function () {
                $("#noticeFragment").css("display","block");
                $("#notice_content").html("processing");
            },
            success: function (response) {
                var json = eval(response);
                oTr.cells[4].innerHTML = json.obj.level;
                oTr.cells[6].innerHTML = json.obj.memberDate;
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

});
