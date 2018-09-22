package net.unmz.java.wechat.pay.dto.response;

import net.unmz.java.wechat.pay.dto.BaseResponseDto;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:26
 * @since JDK 1.8
 */
public class RefundResponseDto extends BaseResponseDto {

    private static final long serialVersionUID = -8359892540545531083L;
    private String transaction_id;//是	String(32)		微信订单号
    private String out_trade_no;//是	    String(32)		商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String out_refund_no;//是	String(64)		商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
    private String refund_id;//是	String(32)	    	微信退款单号
    private String refund_fee;//是	Int	            	退款总金额,单位为分,可以做部分退款
    private String settlement_refund_fee;//否	Int		去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
    private String total_fee;//是	Int	            	订单总金额，单位为分，只能为整数，详见支付金额
    private String settlement_total_fee;//否 	Int		去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
    private String fee_type;//否 	String(8)	    	订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String cash_fee;//是	    Int	            	现金支付金额，单位为分，只能为整数，详见支付金额
    private String cash_fee_type;//否	String(16)		货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String cash_refund_fee;//否	Int	        	现金退款金额，单位为分，只能为整数，详见支付金额
    private String coupon_refund_fee;//否	Int	    	代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠

    private String coupon_refund_count;//否	Int	    	退款代金券使用数量

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

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public void setSettlement_refund_fee(String settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
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

    public String getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(String cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    public String getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(String coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public String getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(String coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }
}
