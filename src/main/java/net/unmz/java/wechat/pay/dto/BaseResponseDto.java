package net.unmz.java.wechat.pay.dto;

import java.io.Serializable;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付响应参数父类
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:33
 * @since JDK 1.8
 */
public class BaseResponseDto implements Serializable {
    private static final long serialVersionUID = 8266370534306161695L;

    private String return_code;//是	String(16)	    SUCCESS
    private String return_msg;//是	String(128)	    OK

    private String appid;//是	String(32)		    微信分配的公众账号ID
    private String mch_id;//是	String(32)		    微信支付分配的商户号
    private String nonce_str;//是	String(32)		随机字符串，不长于32位
    private String sign;//是 	String(32)	        签名，详见签名算法

    private String result_code;//是	String(16)		SUCCESS/FAIL
    private String err_code;//否	String(32)		    详细参见第6节错误列表
    private String err_code_des;//否	String(128)		错误返回的信息描述

    private String sub_appid;//否	String(32)		微信分配的子商户公众账号ID
    private String sub_mch_id;//是	String(32)		微信支付分配的子商户号


    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }
}
