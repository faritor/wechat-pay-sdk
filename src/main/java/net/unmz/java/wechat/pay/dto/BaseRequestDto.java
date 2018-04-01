package net.unmz.java.wechat.pay.dto;

import java.io.Serializable;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付请求参数父类
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:30
 * @since JDK 1.8
 */
public class BaseRequestDto implements Serializable {
    private static final long serialVersionUID = -9144560371736432758L;

    private String appid;//必填   String(32)            微信支付分配的公众账号ID（企业号corpid即为此appId）
    private String mch_id;//必填 	String(32)          微信支付分配的商户号
    private String nonce_str;//必填	String(32)	        随机字符串，长度要求在32位以内。推荐随机数生成算法
    private String sign_type;//非必	String(32)	        MD5	签名类型，默认为MD5，支持HMAC-SHA256和MD5。
    private String sign;//必填 String(32)		        通过签名算法计算得出的签名值，详见签名生成算法

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

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
