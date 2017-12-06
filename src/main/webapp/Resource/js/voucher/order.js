$(function () {

    var tbOrders = $('#tbOrders').get(0).tBodies[0];
    var rowsLength = tbOrders.rows.length;


    showTotalCount();
    $('#ipSearch').keyup(function () {
        var key = $(this).val().toLowerCase();
        if(key.length <= 0){
            showAllRowsOfOrdersTable();
            return;
        }
        for(var i = 0; i < rowsLength; i ++){
            for(var j = 1; j < 5; j ++){
                var content = tbOrders.rows[i].cells[j].innerHTML.toLowerCase();
                if(content.search(key) >= 0){
                    tbOrders.rows[i].style.display = '';
                    break;
                }else{
                    tbOrders.rows[i].style.display = 'none';
                }
            }
        }
        showTotalCount();
    });

    function showAllRowsOfOrdersTable(){
        for(var i =0 ; i < rowsLength; i ++){
            tbOrders.rows[i].style.display = "";
        }
    }

    function showTotalCount() {
        var count = 0;
        for(var i =0 ; i < rowsLength; i ++){
            var status = tbOrders.rows[i].style.display;
            if(status !== 'none'){
                count ++;
            }
        }
        $('#spTotalCount').html(count);
    }

    for(var i = 0; i < rowsLength; i ++){
        var a = tbOrders.rows[i].cells[6].children[0];
        a.onclick = function () {
            if (this.innerHTML === 'completed') {
                var transactionId = this.parentNode.parentNode.children[3].innerHTML;
                showLoading();
                verifyTransaction(this, transactionId)
            }
        }
    }

    function verifyTransaction(a, transactionId) {
        var url = baseVoucherUrl + '/order/verify/' + transactionId;
        $.get(url, function (response) {
            hideLoading();
            console.log(response);
            showNotice(response.message);
            if(response.code === 200){
                a.innerHTML = 'approved';
                a.style.color = '#07ae4a'
            }
        })
    }
});