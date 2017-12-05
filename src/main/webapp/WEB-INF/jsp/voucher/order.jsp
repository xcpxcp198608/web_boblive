<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<rapid:override name="title">
    Orders
</rapid:override>
<rapid:override name="css_js">
    <script type="text/javascript" src="Resource/js/voucher/order.js"></script>
</rapid:override>

<rapid:override name="content">
    <div style="width: 100%; ">

        <div>
            <div style="width: 15%; display: block; float: left;">
                <button type="button" class="btn btn-default" id="btActivate" title="activate">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> A
                </button>

                <button type="button" class="btn btn-default" id="btLimited" title="limited">
                    <span class="glyphicon glyphicon-lock" aria-hidden="true"></span> L
                </button>

                <button type="button" class="btn btn-default" id="btCanceled" title="canceled">
                    <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span> C
                </button>
            </div>

            <div style="width: 80%; display: block; float: left">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">Search</span>
                    <input type="text" class="form-control" placeholder="type in keyword (mac, voucher id, transaction id, time)"
                           aria-describedby="basic-addon1" id="ipSearch">
                </div>
            </div>
            <div style="width: 5%; display: block; float: right; font-size: 18px; font-weight: 500;
            text-align: right; align-content: center">
                <span id="spTotalCount" style="height: 100%; line-height: 100%"></span>
            </div>
        </div>
        <br/>


        <div>
            <table class="table table-bordered table-hover table-striped" id="tbOrders">
                <thead>
                    <tr>
                        <td>#</td>
                        <td>Mac</td>
                        <td>VoucherId</td>
                        <td>TransactionId</td>
                        <td>Amount</td>
                        <td>TransactionTime</td>
                        <td>Verify</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${voucherOrderInfoList}" var="voucherOrderInfo" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${voucherOrderInfo.mac}</td>
                            <td>${voucherOrderInfo.voucherId}</td>
                            <td>${voucherOrderInfo.transactionId}</td>
                            <td>${voucherOrderInfo.amount}</td>
                            <td>${voucherOrderInfo.createTime}</td>
                            <td></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</rapid:override>


<%@ include file="base_voucher.jsp"%>