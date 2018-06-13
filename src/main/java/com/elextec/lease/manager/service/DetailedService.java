package com.elextec.lease.manager.service;

import com.elextec.persist.model.mybatis.Detailed;

import java.util.List;
import java.util.Map;

public interface DetailedService {

    /**
     * 明细列表
     * @param map
     * @return 列表
     */
    public List<Detailed> list(Map<String,Object> map);

    /**
     * 新增明细
     * @param detailed
     */
    public void insterDetailed(Detailed detailed);

    /**
     * 修改明细
     * @param Detailed
     */
    public void updateDetailed(Detailed Detailed);

    /**
     * 删除明细
     * @param Detailed
     */
    public void deleteDetailed(Detailed Detailed);

    /**
     * 根据id查询明细
     * @param id
     * @return 明细
     */
    public Detailed getByPrimaryKey(String id);

}
