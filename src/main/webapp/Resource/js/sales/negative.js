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
                for(var c =0 ; c < 6; c ++) {
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

});
