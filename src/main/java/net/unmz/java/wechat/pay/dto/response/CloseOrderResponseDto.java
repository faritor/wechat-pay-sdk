package net.unmz.java.wechat.pay.dto.response;

import net.unmz.java.wechat.pay.dto.BaseResponseDto;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:25
 * @since JDK 1.8
 */
public class CloseOrderResponseDto extends BaseResponseDto {
    private static final long serialVersionUID = -1074351142028518696L;

    private String result_msg;

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }
}
