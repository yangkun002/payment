package com.elextec.persist.dao.mybatis;

import com.elextec.persist.model.mybatis.Payment;

import java.util.List;

public interface PaymentMapperExt extends PaymentMapper {

    /**
     * 查询所有支付
     * @return 支付方式列表
     */
    public List<Payment> paymentList();

}