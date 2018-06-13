package com.elextec.lease.manager.service.impl;

import com.elextec.lease.manager.service.PaymentPluginService;
import com.elextec.persist.dao.mybatis.PaymentPluginMapperExt;
import com.elextec.persist.model.mybatis.PaymentPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentPluginServiceImpl implements PaymentPluginService {

    /* 日志 */
    private final Logger logger = LoggerFactory.getLogger(PaymentPluginServiceImpl.class);

    @Autowired
    private PaymentPluginMapperExt paymentPluginMapperExt;

    @Override
    public void insterPaymentPlugin(PaymentPlugin paymentPlugin) {

    }

    @Override
    public void updatePaymentPlugin(PaymentPlugin paymentPlugin) {

    }

    @Override
    public void deletePaymentPlugin(PaymentPlugin paymentPlugin) {

    }

    @Override
    public PaymentPlugin getByPrimaryKay(String id) {
        return null;
    }

}
