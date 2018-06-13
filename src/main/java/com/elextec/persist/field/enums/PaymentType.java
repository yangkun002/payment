package com.elextec.persist.field.enums;

/**
 * 支付系统类型
 * create by yangkun 2018/06/13
 */
public enum PaymentType {

    ALIPAY_APPPAY("支付宝APP支付"),

    ALIPAY_SCANNERPAY("支付宝扫码支付"),

    ALIPAY_BARCODEPAY("支付宝条码支付"),

    ALIPAY_MOBILEWEBPAY("支付宝手机网站支付"),

    ALIPAY_WEBSITEPAY("支付宝网站支付"),

    ALIPAY_HUABEIPAY("支付宝花呗"),

    WEWHAT_APPPAY("微信APP支付"),

    WEWHAT_PUBLICNUMBERPAY("微信公众号支付"),

    WEWHAT_SCANNERPAY("微信扫码支付"),

    WEWHAT_CARDPAY("微信刷卡支付"),

    WEWHAT_WEPAY("微信买单");

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
     * @param info
     */
    private PaymentType(String info){
        this.info = info;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
