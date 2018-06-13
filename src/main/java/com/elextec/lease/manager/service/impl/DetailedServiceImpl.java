package com.elextec.lease.manager.service.impl;

import com.elextec.lease.manager.service.DetailedService;
import com.elextec.persist.dao.mybatis.DetailedMapperExt;
import com.elextec.persist.model.mybatis.Detailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DetailedServiceImpl implements DetailedService {

    /* 日志 */
    private final Logger logger = LoggerFactory.getLogger(DetailedServiceImpl.class);

    @Autowired
    private DetailedMapperExt detailedMapperExt;

    @Override
    public List<Detailed> list(Map<String,Object> map) {
        return null;
    }

    @Override
    public void insterDetailed(Detailed detailed) {

    }

    @Override
    public void updateDetailed(Detailed Detailed) {

    }

    @Override
    public void deleteDetailed(Detailed Detailed) {

    }

    @Override
    public Detailed getByPrimaryKey(String id) {
        return null;
    }

}
