package net.unmz.java.wechat.pay.dto.request;

import net.unmz.java.wechat.pay.dto.BaseRequestDto;

/**
 * Project Name:
 * 功能描述： 退款查询
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:26
 * @since JDK 1.8
 */
public class RefundQueryRequestDto extends BaseRequestDto {

    private static final long serialVersionUID = 560425492977943180L;

    private String transaction_id;//四选一 String(28)		微信订单号 查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
    private String out_trade_no;//四选一   String(32)		商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String out_refund_no;//四选一  String(32)		商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String refund_id;//四选一      String(32)	    微信退款单号 refund_id、out_refund_no、out_trade_no、transaction_id四个参数必填一个，如果同时存在优先级为：>out_refund_no>transaction_id>out_trade_no
    private String offset;//否	            Int	            偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录

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

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
