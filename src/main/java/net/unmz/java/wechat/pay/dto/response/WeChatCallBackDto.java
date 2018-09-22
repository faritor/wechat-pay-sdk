package net.unmz.java.wechat.pay.dto.response;

import net.unmz.java.wechat.pay.dto.BaseResponseDto;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/4/9 15:00
 * @since JDK 1.8
 */
public class WeChatCallBackDto extends BaseResponseDto {

    private static final long serialVersionUID = -1444210270642187032L;
    private String device_info;//否	String(32)		微信支付分配的终端设备号，
    /**
     * 普通商户支付回调中使用,服务商回调中无此参数
     */
    private String sign_type;//否	String(32)		签名类型，目前支持HMAC-SHA256和MD5，默认为MD5

    private String openid;//是	String(128)		    用户在商户appid下的唯一标识
    private String is_subscribe;//是	    String(1)	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private String trade_type;//是	String(16)	    JSAPI、NATIVE、APP
    private String bank_type;//是	String(16)	    银行类型，采用字符串类型的银行标识，银行类型见附表
    private String total_fee;//是	Int	    	    订单总金额，单位为分
    private String settlement_total_fee;//否	Int	    应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
    private String fee_type;//否 	String(8)	    货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String cash_fee;//是	    Int		        现金支付金额订单现金支付金额，详见支付金额
    private String cash_fee_type;//否    String(16)	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String coupon_fee;//否	Int	    	    代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额，详见支付金额
    private String coupon_count;//否	    Int		    代金券或立减优惠使用数量
    private String transaction_id;//是	String(32)	微信支付订单号
    private String out_trade_no;//是 	String(32)	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String attach;//否	String(128)	    	商家数据包，原样返回
    private String time_end;//是	String(14)	    	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则


    private String sub_openid;//否	String(128)		用户在子商户appid下的唯一标识
    private String sub_is_subscribe;//否	String(1)	用户是否关注子公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效

    private String result_wecaht_message;

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(String coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getSub_openid() {
        return sub_openid;
    }

    public void setSub_openid(String sub_openid) {
        this.sub_openid = sub_openid;
    }

    public String getSub_is_subscribe() {
        return sub_is_subscribe;
    }

    public void setSub_is_subscribe(String sub_is_subscribe) {
        this.sub_is_subscribe = sub_is_subscribe;
    }

    public String getResult_wecaht_message() {
        return result_wecaht_message;
    }

    public void setResult_wecaht_message(String result_wecaht_message) {
        this.result_wecaht_message = result_wecaht_message;
    }
}
