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
            if(oTbody.rows[n].cells[3].innerHTML == $("#sLevel").val() || $("#sLevel").val() == 0 ){
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
            if(oTbody.rows[n].cells[6].children[0].getAttribute("alt") == $("#sOnline").val() || $("#sOnline").val() ==0 ){
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
            if(oTbody.rows[n].cells[7].children[0].getAttribute("alt") == $("#sStatus").val() || $("#sStatus").val() == 0){
                oTbody.rows[n].style.display = "";
            }else{
                oTbody.rows[n].style.display = "none";
            }
        }
        showTotalCount();
    });

});
