package net.unmz.java.wechat.pay;

import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatResponseCodeEnum;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.CloseOrderRequestDto;
import net.unmz.java.wechat.pay.dto.response.CloseOrderResponseDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付关闭订单接口
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-4-1 2:32
 * @since JDK 1.8
 */
public class WeChatCloseOrder extends WeChatPay {

    /**
     * 微信关闭订单接口执行方法
     *
     * @param dto 关闭订单请求实体
     * @return
     * @throws Exception
     */
    @Override
    public BaseResponseDto execute(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.CLOSE_ORDER.getUrl());
        CloseOrderResponseDto responseDto = JsonUtils.toBean(XmlUtils.toString(result, "utf-8"), CloseOrderResponseDto.class);
        System.out.println("WeChat return message : " + JsonUtils.toJSON(responseDto));
        if (WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getResult_code())
                && WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getReturn_code()))
            return responseDto;
        else if(StringUtils.isNotBlank(responseDto.getErr_code()))
            throw new WeChatException(responseDto.getErr_code_des());
        throw new WeChatException(responseDto.getResult_msg());
    }

    /**
     * 校验必填请求参数
     *
     * @param dto 关闭订单请求实体
     */
    @Override
    protected void validateParams(BaseRequestDto dto) {
        super.validateParams(dto);
        CloseOrderRequestDto requestDto = (CloseOrderRequestDto) dto;
        if (StringUtils.isBlank(requestDto.getOut_trade_no()))
            throw new IllegalArgumentException("WeChat Request params out_trade_no is null");

        if (requestDto.getOut_trade_no().length() > 32)
            throw new IllegalArgumentException("WeChat Request params out_trade_no is too long");
    }
}
