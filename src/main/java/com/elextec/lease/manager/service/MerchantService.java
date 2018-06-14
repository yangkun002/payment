package com.elextec.lease.manager.service;

import com.elextec.persist.model.mybatis.Merchant;

import java.util.List;
import java.util.Map;

public interface MerchantService {

    /**
     * 商户列表
     * @param map 商户
     * @return 列表
     */
    public List<Merchant> list(Map<String,Object> map);

    /**
     * 新增商户
     * @param merchant
     */
    public void insterMerchant(Merchant merchant);

    /**
     * 修改商户
     * @param merchant
     */
    public void updateMerchant(Merchant merchant);

    /**
     * 删除商户
     * @param merchant
     */
    public void deleteMerchant(Merchant merchant);

    /**
     * 根据id查询商户
     * @param id
     * @return 商户
     */
    public Merchant getByPrimaryKay(String id);

}
