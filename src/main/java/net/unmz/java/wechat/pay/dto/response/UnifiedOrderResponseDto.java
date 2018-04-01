package net.unmz.java.wechat.pay.dto.response;

import net.unmz.java.wechat.pay.dto.BaseResponseDto;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:24
 * @since JDK 1.8
 */
public class UnifiedOrderResponseDto extends BaseResponseDto {
    private static final long serialVersionUID = 448987826851402297L;


    private String device_info;

    private String trade_type;
    private String prepay_id;
    private String code_url;


    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}
