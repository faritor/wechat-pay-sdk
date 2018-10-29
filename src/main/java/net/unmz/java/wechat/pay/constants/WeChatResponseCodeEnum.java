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
    TRADE_STATE_SUCCESS("SUCCESS","支付成功"),
    TRADE_STATE_REFUND("REFUND","转入退款"),
    TRADE_STATE_NOTPAY("NOTPAY","未支付"),
    TRADE_STATE_CLOSED("CLOSED","已关闭"),
    TRADE_STATE_REVOKED("REVOKED","已撤销（刷卡支付）"),
    TRADE_STATE_USERPAYING("USERPAYING","用户支付中"),
    TRADE_STATE_PAYERROR("PAYERROR","支付失败(其他原因，如银行返回失败)"),

    //错误码
    ERR_CODE_ORDER_NOT_EXIST("ORDERNOTEXIST","order not exist"),


    //创建订单返回错误码
    TRADE_CREATE_ERROR_CODE_NOAUTH("NOAUTH","商户无此接口权限"),
    TRADE_CREATE_ERROR_CODE_NOTENOUGH("NOTENOUGH", "余额不足"),
    TRADE_CREATE_ERROR_CODE_ORDERPAID("ORDERPAID", "商户订单已支付"),
    TRADE_CREATE_ERROR_CODE_ORDERCLOSED("ORDERCLOSED", "订单已关闭"),
    TRADE_CREATE_ERROR_CODE_SYSTEMERROR("SYSTEMERROR","系统错误"),
    TRADE_CREATE_ERROR_CODE_APPID_NOT_EXIST("APPID_NOT_EXIST","APPID不存在"),
    TRADE_CREATE_ERROR_CODE_MCHID_NOT_EXIST("MCHID_NOT_EXIST","MCHID不存在"),
    TRADE_CREATE_ERROR_CODE_APPID_MCHID_NOT_MATCH("APPID_MCHID_NOT_MATCH", "appid和mch_id不匹配"),
    TRADE_CREATE_ERROR_CODE_LACK_PARAMS("LACK_PARAMS", "缺少参数"),
    TRADE_CREATE_ERROR_CODE_OUT_TRADE_NO_USED("OUT_TRADE_NO_USED","商户订单号重复"),
    TRADE_CREATE_ERROR_CODE_SIGNERROR("SIGNERROR", "签名错误"),
    TRADE_CREATE_ERROR_CODE_XML_FORMAT_ERROR("XML_FORMAT_ERROR","XML格式错误"),
    TRADE_CREATE_ERROR_CODE_REQUIRE_POST_METHOD("REQUIRE_POST_METHOD","请使用post方法"),
    TRADE_CREATE_ERROR_CODE_POST_DATA_EMPTY("POST_DATA_EMPTY", "post数据为空"),
    TRADE_CREATE_ERROR_CODE_NOT_UTF8("NOT_UTF8","编码格式错误"),


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
