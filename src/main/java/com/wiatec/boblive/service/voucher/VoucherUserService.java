package com.wiatec.boblive.service.voucher;

import com.wiatec.boblive.Constant;
import com.wiatec.boblive.common.result.*;
import com.wiatec.boblive.common.utils.TimeUtil;
import com.wiatec.boblive.common.utils.TokenUtil;
import com.wiatec.boblive.orm.dao.voucher.VoucherOrderDao;
import com.wiatec.boblive.orm.dao.voucher.VoucherUserDao;
import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserInfo;
import com.wiatec.boblive.voucher.VoucherInfo;
import com.wiatec.boblive.voucher.VoucherMaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author patrick
 */
@Service
public class VoucherUserService {

    @Resource
    private VoucherUserDao voucherUserDao;
    @Resource
    private VoucherOrderDao voucherOrderDao;

    /**
     * activate voucher user
     * @param voucherUserInfo required: voucher id, mac, days, price
     * @return ResultInfo
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo activate(VoucherUserInfo voucherUserInfo, String lang){
        //1.
        VoucherOrderInfo voucherOrderInfo = voucherOrderDao.selectByMacAndVoucherId(voucherUserInfo.getMac(),
                voucherUserInfo.getVoucherId());
        if(voucherOrderInfo != null){
            VoucherInfo voucherInfo = new VoucherInfo();
            voucherInfo.setVoucher(Long.parseLong(voucherOrderInfo.getVoucherId()));
            voucherInfo.setTransaction(voucherOrderInfo.getTransactionId());
            voucherInfo.setAuth(voucherOrderInfo.getAuth());
            boolean confirm = VoucherMaster.confirm(voucherInfo);
            if (!confirm) {
                if(Constant.LANG_CS_CZ.equals(lang)) {
                    throw new XException(EnumResultCZ.ERROR_VALIDATE_FAILURE);
                }else {
                    throw new XException(1009, "voucher pay confirm failure");
                }
            }
        }else {
            String transactionId = TokenUtil.create16(voucherUserInfo.getMac());
            VoucherInfo voucherInfo = VoucherMaster.pay(voucherUserInfo.getVoucherId(), transactionId,
                    voucherUserInfo.getPrice());
            if (!VoucherOrderInfo.CURRENCY_CZK.equals(voucherInfo.getCurrency())) {
                if(Constant.LANG_CS_CZ.equals(lang)) {
                    throw new XException(EnumResultCZ.ERROR_VALIDATE_FAILURE);
                }else {
                    throw new XException(1010, "currency no match");
                }
            }
            voucherOrderDao.insertOne(VoucherOrderInfo.createForSaveOrder(voucherUserInfo, voucherInfo));
            boolean confirm = VoucherMaster.confirm(voucherInfo);
            if (!confirm) {
                if(Constant.LANG_CS_CZ.equals(lang)) {
                    throw new XException(EnumResultCZ.ERROR_VALIDATE_FAILURE);
                }else {
                    throw new XException(1009, "voucher pay confirm failure");
                }
            }
        }
        //2.
        voucherUserInfo.setLevel(2);
        voucherUserInfo.setActivateTime(TimeUtil.getStrTime());
        if(voucherUserDao.countOneByMac(voucherUserInfo) == 1){
            VoucherUserInfo v1 = voucherUserDao.selectOneByMac(voucherUserInfo.getMac());
            if(TimeUtil.isOutExpires(v1.getExpiresTime())){
                voucherUserInfo.setExpiresTime(TimeUtil.getExpiresTimeWithDay(voucherUserInfo.getDays()));
            }else {
                voucherUserInfo.setExpiresTime(TimeUtil.getExpiresTimeWithDay(v1.getExpiresTime(), voucherUserInfo.getDays()));
            }
            voucherUserDao.updateByMac(voucherUserInfo);
        }else{
            voucherUserInfo.setExpiresTime(TimeUtil.getExpiresTimeWithDay(voucherUserInfo.getDays()));
            voucherUserDao.insertOne(voucherUserInfo);
        }
        //3.
        voucherOrderDao.updateStatusToConfirm(voucherUserInfo.getMac());
        if(Constant.LANG_CS_CZ.equals(lang)) {
            return ResultMaster.successCZ(voucherUserInfo);
        }else {
            return ResultMaster.success(voucherUserInfo);
        }
    }

    public ResultInfo validate(String mac, HttpSession session, String lang){
        VoucherUserInfo voucherUserInfo1 = voucherUserDao.selectOneByMac(mac);
        if(voucherUserInfo1 == null){
            throw new XException(EnumResult.ERROR_DEVICE_NO_REGISTER);
        }
        if(TimeUtil.isOutExpires(voucherUserInfo1.getExpiresTime())){
            throw new XException(EnumResult.ERROR_OUT_EXPIRATION);
        }
        session.setAttribute("voucherKey", mac);
        return ResultMaster.success(voucherUserInfo1);
    }

}
