package net.unmz.java.wechat.pay;

import net.unmz.java.util.data.DataLengthCheckHelper;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatResponseCodeEnum;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.OrderQueryRequestDto;
import net.unmz.java.wechat.pay.dto.response.OrderQueryResponseDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付订单查询接口
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-4-1 1:57
 * @since JDK 1.8
 */
public class WeChatOrderQuery extends WeChatPay {

    /**
     * 微信查询订单接口执行方法
     *
     * @param dto 查询订单请求实体
     * @return
     * @throws Exception
     */
    @Override
    public BaseResponseDto execute(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.ORDER_QUERY.getUrl());
        OrderQueryResponseDto responseDto = XmlUtils.toBean(result, OrderQueryResponseDto.class);
        System.out.println("WeChat return message : " + JsonUtils.toJSON(responseDto));
        if (WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getResult_code())
                && WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getReturn_code())
                && WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getTrade_state()))
            return responseDto;
        else if (StringUtils.isNotBlank(responseDto.getErr_code()))
            throw new WeChatException(responseDto.getErr_code(), responseDto.getErr_code_des());
        throw new WeChatException(responseDto.getTrade_state(), responseDto.getTrade_state_desc());
    }

    /**
     * 校验必填请求参数
     *
     * @param dto 查询订单请求实体
     */
    @Override
    protected void validateParams(BaseRequestDto dto) {
        super.validateParams(dto);
        OrderQueryRequestDto requestDto = (OrderQueryRequestDto) dto;
        DataLengthCheckHelper.validateAttributeValueLength(requestDto);
    }
}
