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

    //常规接口返回值
    SUCCESS("SUCCESS", "成功"),
    FAIL("FAIL","失败"),


    //交易类型
    TRADE_TYPE_JSAPI("JSAPI","JSAPI 公众号支付"),
    TRADE_TYPE_NATIVE("NATIVE","NATIVE 扫码支付"),
    TRADE_TYPE_APP("APP","APP APP支付"),


    //订单状态
    TRADE_STATE_SUCCESS("SUCCESS","SUCCESS—支付成功"),
    TRADE_STATE_REFUND("REFUND","REFUND—转入退款"),
    TRADE_STATE_NOTPAY("NOTPAY","NOTPAY—未支付"),
    TRADE_STATE_CLOSED("CLOSED","CLOSED—已关闭"),
    TRADE_STATE_REVOKED("REVOKED","REVOKED—已撤销（刷卡支付）"),
    TRADE_STATE_USERPAYING("USERPAYING","USERPAYING--用户支付中"),
    TRADE_STATE_PAYERROR("PAYERROR","PAYERROR--支付失败(其他原因，如银行返回失败)"),


    //错误码
    ERR_CODE_ORDER_NOT_EXIST("ORDERNOTEXIST","order not exist"),
    ;

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
