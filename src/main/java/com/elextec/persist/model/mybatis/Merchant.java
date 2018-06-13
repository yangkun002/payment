package com.elextec.persist.model.mybatis;

import java.io.Serializable;

public class Merchant implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table merchant
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = -5459288658330184539L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column merchant.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column merchant.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column merchant.sign
     *
     * @mbggenerated
     */
    private String sign;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column merchant.id
     *
     * @return the value of merchant.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column merchant.id
     *
     * @param id the value for merchant.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column merchant.name
     *
     * @return the value of merchant.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column merchant.name
     *
     * @param name the value for merchant.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column merchant.sign
     *
     * @return the value of merchant.sign
     *
     * @mbggenerated
     */
    public String getSign() {
        return sign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column merchant.sign
     *
     * @param sign the value for merchant.sign
     *
     * @mbggenerated
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }
}