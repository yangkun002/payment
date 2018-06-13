package com.elextec.persist.field.enums;

/**
 * 业务流水交易状态
 * create by yangkun 2018/06/13
 */
public enum BusinessType {

    PAYMENT_UNDETERMINED("待支付"),

    ALREADY_PAID("已支付"),

    REFUND_UNDETERMINED("退款中"),

    REFUND_COMPLETE("已退款"),

    REVOKE_UNDETERMINED("撤销中"),

    REVOKE_COMPLETE("撤销完成"),

    CLOSE_UNDETERMINED("关闭中"),

    CLOSE_COMPLETE("关闭完成");

    /**
     * value
     */
    public final String info;

    /**
     * get and set 方法
     * @return
     */
    public String getInfo() {
        return info;
    }

    /**
     * 构造函数
     * @return
     */
    private BusinessType(String info){
        this.info = info;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
