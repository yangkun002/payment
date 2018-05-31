package com.elextec.lease.manager.service;

import com.elextec.persist.model.mybatis.Payment;

import java.util.List;

public interface PaymentService {

    /**
     * 查询所有支付方式
     * @return 支付方式列表
     */
    public List<Payment> paymentList();

    /**
     *  插入支付对象
     * @param record 支付对象
     */
    public void insert(Payment record);

}
