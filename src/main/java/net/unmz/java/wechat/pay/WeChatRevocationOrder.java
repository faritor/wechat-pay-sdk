package net.unmz.java.wechat.pay;

import net.unmz.java.util.data.DataLengthCheckHelper;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatResponseCodeEnum;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.ReverseRequestDto;
import net.unmz.java.wechat.pay.dto.request.UnifiedOrderRequestDto;
import net.unmz.java.wechat.pay.dto.response.ReverseResponseDto;
import net.unmz.java.wechat.pay.dto.response.UnifiedOrderResponseDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付撤销订单接口
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/4/2 12:18
 * @since JDK 1.8
 */
public class WeChatRevocationOrder extends WeChatPay {

    @Override
    public BaseResponseDto execute(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.REVOCATION_ORDER.getUrl());
        ReverseResponseDto responseDto = XmlUtils.toBean(result, ReverseResponseDto.class);
        System.out.println("WeChat return message : " + JsonUtils.toJSON(responseDto));
        if (WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getResult_code())
                && WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getReturn_code()))
            return responseDto;
        else if (StringUtils.isNotBlank(responseDto.getErr_code()))
            throw new WeChatException(responseDto.getErr_code(), responseDto.getErr_code_des());
        throw new WeChatException(responseDto.getReturn_code(), responseDto.getReturn_msg());
    }

    @Override
    public Map<String, String> executeRespMap(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.REVOCATION_ORDER.getUrl());
        return XmlUtils.toMap(result.getBytes(), StandardCharsets.UTF_8.displayName());
    }

    /**
     * 校验必填请求参数
     *
     * @param dto 统一下单请求实体
     */
    @Override
    protected void validateParams(BaseRequestDto dto) {
        super.validateParams(dto);
        ReverseRequestDto requestDto = (ReverseRequestDto) dto;
        DataLengthCheckHelper.validateAttributeValueLength(requestDto);
    }
}
