package com.elextec.lease.manager.service;

import com.elextec.persist.model.mybatis.Business;

import java.util.List;
import java.util.Map;

public interface BusinessService {

    /**
     * 查询流水
     * @param map
     * @return
     */
    public List<Business> list(Map<String,Object> map);

    /**
     * 新增流水
     * @param business
     */
    public void insterBusiness(Business business);

    /**
     * 修改流水
     * @param business
     */
    public void updateBusiness(Business business);

    /**
     * 删除流水
     * @param business
     */
    public void deleteBusiness(Business business);

    /**
     * 根据id查询流水
     * @param id
     * @return
     */
    public Business getByPrimaryKey(String id);

}
