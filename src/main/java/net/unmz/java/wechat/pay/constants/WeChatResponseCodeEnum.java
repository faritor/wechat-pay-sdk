package net.unmz.java.wechat.pay.constants;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付状态码定义
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:52
 * @since JDK 1.8
 */
public enum WeChatResponseCodeEnum {

    SUCCESS("SUCCESS", "成功"),;

    WeChatResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
