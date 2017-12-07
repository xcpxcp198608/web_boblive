package com.wiatec.boblive.service.voucher;

import com.wiatec.boblive.common.result.EnumResult;
import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import com.wiatec.boblive.common.utils.TimeUtil;
import com.wiatec.boblive.common.utils.TokenUtil;
import com.wiatec.boblive.orm.dao.voucher.VoucherOrderDao;
import com.wiatec.boblive.orm.dao.voucher.VoucherUserCategoryDao;
import com.wiatec.boblive.orm.dao.voucher.VoucherUserDao;
import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserCategoryInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserInfo;
import com.wiatec.boblive.voucher.VoucherInfo;
import com.wiatec.boblive.voucher.VoucherMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class VoucherUserService {

    private final Logger logger = LoggerFactory.getLogger(VoucherUserService.class);

    @Resource
    private VoucherUserCategoryDao voucherUserCategoryDao;
    @Resource
    private VoucherUserDao voucherUserDao;
    @Resource
    private VoucherOrderDao voucherOrderDao;

    public ResultInfo<VoucherUserCategoryInfo> listCategory(){
        return ResultMaster.success(voucherUserCategoryDao.selectAll());
    }

    @Transactional
    public ResultInfo activate(VoucherUserInfo voucherUserInfo){
        try {
            VoucherUserCategoryInfo voucherUserCategoryInfo = voucherUserCategoryDao
                    .selectOneByCategory(voucherUserInfo.getCategory());
            int month = voucherUserInfo.getMonth();
            float price = voucherUserCategoryInfo.getPrice() * month;
            String transactionId = TokenUtil.create(voucherUserInfo.getMac(), System.currentTimeMillis() + "");
            VoucherInfo voucherInfo = VoucherMaster.pay(voucherUserInfo.getVoucherId(), transactionId);
            float amount = voucherInfo.getVoucher().getAmount() / 100;
            if(amount < price){
                throw new XException(ResultMaster.error(1007, "voucher amount no enough"));
            }
            if(!VoucherMaster.confirm(voucherUserInfo.getVoucherId(), price)){
                throw new XException(ResultMaster.error(1007, "voucher pay confirm failed"));
            }
            voucherUserInfo.setLevel(voucherUserCategoryInfo.getLevel());
            int expires = 0;
            if (month > voucherUserCategoryInfo.getStartMonth()) {
                expires = month + voucherUserCategoryInfo.getBonus();
            } else {
                expires = month;
            }
            voucherUserInfo.setActivateTime(TimeUtil.getStrTime());
            voucherUserInfo.setExpiresTime(TimeUtil.getExpiresTime(expires));
            if(voucherUserDao.countOneByMac(voucherUserInfo) == 1) {
                voucherUserDao.updateByMac(voucherUserInfo);
            }else{
                voucherUserDao.insertOne(voucherUserInfo);
            }
            VoucherOrderInfo voucherOrderInfo = new VoucherOrderInfo();
            if(VoucherMaster.verifyTransaction(transactionId)){
                voucherOrderInfo.setStatus(VoucherOrderInfo.STATUS_APPROVED);
            }else{
                voucherOrderInfo.setStatus(VoucherOrderInfo.STATUS_COMPLETED);
            }
            voucherOrderInfo.setTransactionId(transactionId);
            voucherOrderInfo.setMac(voucherUserInfo.getMac());
            voucherOrderInfo.setVoucherId(voucherUserInfo.getVoucherId());
            voucherOrderInfo.setPrice(price);
            voucherOrderInfo.setAmount(amount);
            voucherOrderInfo.setCurrency(voucherInfo.getVoucher().getCurrency());
            voucherOrderInfo.setOwner(voucherInfo.getVoucher().getOwner());
            voucherOrderInfo.setFirstName(voucherInfo.getUser().getFirstname());
            voucherOrderInfo.setSurname(voucherInfo.getUser().getSurname());
            voucherOrderInfo.setEmail(voucherInfo.getUser().getEmail());
            voucherOrderInfo.setGender(voucherInfo.getUser().getGender());
            voucherOrderInfo.setBirthday(voucherInfo.getUser().getBirthday());
            voucherOrderInfo.setCountry(voucherInfo.getUser().getCountry());
            voucherOrderInfo.setCity(voucherInfo.getUser().getCity());
            voucherOrderInfo.setAddress(voucherInfo.getUser().getAddress());
            voucherOrderDao.insertOne(voucherOrderInfo);
            return ResultMaster.success(voucherUserInfo);
        }catch (Exception e){
            logger.debug(e.getMessage());
            throw new XException(1009, e.getMessage());
        }
    }

    public ResultInfo validate(String mac, HttpSession session){
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
