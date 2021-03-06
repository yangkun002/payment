package com.elextec.persist.model.mybatis;

import com.elextec.persist.field.enums.PaymentType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Business implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table business
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = -5459288946734527843L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.mch_id
     *
     * @mbggenerated
     */
    private String mchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.type
     *
     * @mbggenerated
     */
    private PaymentType Type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.money
     *
     * @mbggenerated
     */
    private BigDecimal money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.amount
     *
     * @mbggenerated
     */
    private Integer amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.state
     *
     * @mbggenerated
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.trading_time
     *
     * @mbggenerated
     */
    private Date tradingTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.id
     *
     * @return the value of business.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.id
     *
     * @param id the value for business.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.mch_id
     *
     * @return the value of business.mch_id
     *
     * @mbggenerated
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.mch_id
     *
     * @param mchId the value for business.mch_id
     *
     * @mbggenerated
     */
    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.type
     *
     * @return the value of business.type
     *
     * @mbggenerated
     */
    public PaymentType getType() {
        return Type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.type
     *
     * @param Type the value for business.type
     *
     * @mbggenerated
     */
    public void setType(PaymentType Type) {
        this.Type = Type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.name
     *
     * @return the value of business.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.name
     *
     * @param name the value for business.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.money
     *
     * @return the value of business.money
     *
     * @mbggenerated
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.money
     *
     * @param money the value for business.money
     *
     * @mbggenerated
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.amount
     *
     * @return the value of business.amount
     *
     * @mbggenerated
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.amount
     *
     * @param amount the value for business.amount
     *
     * @mbggenerated
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.state
     *
     * @return the value of business.state
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.state
     *
     * @param state the value for business.state
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.trading_time
     *
     * @return the value of business.trading_time
     *
     * @mbggenerated
     */
    public Date getTradingTime() {
        return tradingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.trading_time
     *
     * @param tradingTime the value for business.trading_time
     *
     * @mbggenerated
     */
    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }
}