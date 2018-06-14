package com.elextec.lease.manager.service.impl;

import com.elextec.lease.manager.service.MerchantService;
import com.elextec.persist.dao.mybatis.MerchantMapperExt;
import com.elextec.persist.model.mybatis.Merchant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MerchantServiceImpl implements MerchantService {

    /* 日志 */
    private final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantMapperExt merchantMapperExt;

    @Override
    public List<Merchant> list(Map<String,Object> map) {
        return null;
    }

    @Override
    public void insterMerchant(Merchant merchant) {

    }

    @Override
    public void updateMerchant(Merchant merchant) {

    }

    @Override
    public void deleteMerchant(Merchant merchant) {

    }

    @Override
    public Merchant getByPrimaryKay(String id) {
        return null;
    }
}
