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
public class CloseOrderRequestDto extends BaseRequestDto {
    private static final long serialVersionUID = -1349906684158740888L;

    private String out_trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
