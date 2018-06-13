package com.elextec.lease.manager.service;

import com.elextec.persist.model.mybatis.PaymentPlugin;

public interface PaymentPluginService {

    /**
     * 插入支付配置信息
     * @param paymentPlugin
     */
    public void insterPaymentPlugin(PaymentPlugin paymentPlugin);

    /**
     * 修改支付配置信息
     * @param paymentPlugin
     */
    public void updatePaymentPlugin(PaymentPlugin paymentPlugin);

    /**
     * 删除支付配置
     * @param paymentPlugin
     */
    public void deletePaymentPlugin(PaymentPlugin paymentPlugin);

    /**
     * 根据id查询支付配置信息
     * @param id
     * @return 支付配置信息
     */
    public PaymentPlugin getByPrimaryKay(String id);

}
