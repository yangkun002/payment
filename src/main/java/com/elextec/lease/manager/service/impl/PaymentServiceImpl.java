package com.elextec.lease.manager.service.impl;

import com.elextec.framework.common.constants.RunningResult;
import com.elextec.framework.exceptions.BizException;
import com.elextec.framework.utils.WzUniqueValUtil;
import com.elextec.lease.manager.service.PaymentService;
import com.elextec.persist.dao.mybatis.PaymentMapperExt;
import com.elextec.persist.model.mybatis.Payment;
import com.elextec.persist.model.mybatis.PaymentExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    /**日志*/
    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentMapperExt paymentMapperExt;

    @Override
    public List<Payment> paymentList() {
        return paymentMapperExt.paymentList();
    }

    @Override
    @Transactional
    public void insert(Payment record) {
        PaymentExample paymentExample = new PaymentExample();
        PaymentExample.Criteria criteria = paymentExample.createCriteria();
        criteria.andIdEqualTo(record.getId());
        criteria.andNameEqualTo(record.getName());
        if (paymentMapperExt.countByExample(paymentExample) != 0) {
            throw new BizException(RunningResult.MULTIPLE_RECORD.code(),"支付系统("+record.getName()+")已存在");
        }
        record.setId(WzUniqueValUtil.makeUUID());
        record.setCreateTime(new Date());
        paymentMapperExt.insert(record);
    }

    @Override
    public void updatePayment(Payment record) {
        PaymentExample paymentExample = new PaymentExample();
        PaymentExample.Criteria criteria = paymentExample.createCriteria();
        criteria.andIdEqualTo(record.getId());
        if (paymentMapperExt.countByExample(paymentExample) == 0) {
            throw new BizException(RunningResult.NO_USER.code(),"该支付方式不存在");
        }
        record.setUpdateTime(new Date());
        paymentMapperExt.updateByPrimaryKey(record);
    }
}
