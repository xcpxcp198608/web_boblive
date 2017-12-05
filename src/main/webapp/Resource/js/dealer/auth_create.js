/**
 * Created by patrick on 25/08/2017.
 */
function salesSelected(obj) {
    var oBtCreate = $g("btCreate");
    var index = obj.selectedIndex;
    if(index == 0){
        oBtCreate.setAttribute("disabled", "disabled");
        return;
    }
    oBtCreate.removeAttribute("disabled");
}





