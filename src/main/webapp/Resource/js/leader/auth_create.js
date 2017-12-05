/**
 * Created by patrick on 25/08/2017.
 */
function dealerSelected(obj) {
    var oSalesSelect = $g("salesSelect");
    var oBtCreate = $g("btCreate");
    var index = obj.selectedIndex;
    if(index == 0){
        oSalesSelect.setAttribute("disabled", "disabled");
        oBtCreate.setAttribute("disabled", "disabled");
        return;
    }
    oSalesSelect.removeAttribute("disabled");
    oBtCreate.removeAttribute("disabled");
    oSalesSelect.options.length = 0;
    var dealer = obj.options[index].value;
    var url = "http://"+location.host+"/boblive/sales/";
    $.ajax({
        type:"POST",
        url: url,
        data: {"dealer":dealer},
        dataType: "json",
        success: function (data) {
            addSalesSelect(data);
        }
    })
}

function addSalesSelect(data) {
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





