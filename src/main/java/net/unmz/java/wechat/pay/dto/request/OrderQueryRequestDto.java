package net.unmz.java.wechat.pay.dto.request;

import net.unmz.java.wechat.pay.dto.BaseRequestDto;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:25
 * @since JDK 1.8
 */
public class OrderQueryRequestDto extends BaseRequestDto {
    private static final long serialVersionUID = 362897404814259218L;

    private String transaction_id;
    private String out_trade_no;

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
}
