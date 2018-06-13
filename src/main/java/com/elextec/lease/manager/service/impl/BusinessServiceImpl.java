package com.elextec.lease.manager.service.impl;

import com.elextec.lease.manager.service.BusinessService;
import com.elextec.persist.dao.mybatis.BusinessMapperExt;
import com.elextec.persist.model.mybatis.Business;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusinessServiceImpl implements BusinessService {

    /* 日志 */
    private final Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Autowired
    private BusinessMapperExt businessMapperExt;

    @Override
    public List<Business> list(Map<String,Object> map) {
        return null;
    }

    @Override
    public void insterBusiness(Business business) {

    }

    @Override
    public void updateBusiness(Business business) {

    }

    @Override
    public void deleteBusiness(Business business) {

    }

    @Override
    public Business getByPrimaryKey(String id) {
        return null;
    }
}
