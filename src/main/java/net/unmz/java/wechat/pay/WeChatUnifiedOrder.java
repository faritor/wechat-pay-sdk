package net.unmz.java.wechat.pay;

import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatResponseCodeEnum;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.UnifiedOrderRequestDto;
import net.unmz.java.wechat.pay.dto.response.UnifiedOrderResponseDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付统一下单接口
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-4-1 0:02
 * @since JDK 1.8
 */
public class WeChatUnifiedOrder extends WeChatPay {

    /**
     * 微信统一下单接口执行方法
     *
     * @param dto 统一下单请求实体
     * @return
     * @throws Exception
     */
    @Override
    public BaseResponseDto execute(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.UNIFIED_ORDER.getUrl());
        UnifiedOrderResponseDto responseDto = JsonUtils.toBean(XmlUtils.toString(result, "utf-8"), UnifiedOrderResponseDto.class);
        System.out.println("WeChat return message : " + JsonUtils.toJSON(responseDto));
        if (WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getResult_code())
                && WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getReturn_code()))
            return responseDto;
        else if (StringUtils.isNotBlank(responseDto.getErr_code()))
            throw new WeChatException(responseDto.getErr_code_des());
        throw new WeChatException(responseDto.getReturn_msg());
    }

    /**
     * 校验必填请求参数
     *
     * @param dto 统一下单请求实体
     */
    @Override
    protected void validateParams(BaseRequestDto dto) {
        super.validateParams(dto);
        UnifiedOrderRequestDto requestDto = (UnifiedOrderRequestDto) dto;
        if (StringUtils.isBlank(requestDto.getBody()))
            throw new IllegalArgumentException("WeChat Request params body is null");
        if (StringUtils.isBlank(requestDto.getOut_trade_no()))
            throw new IllegalArgumentException("WeChat Request params out_trade_no is null");
        if (StringUtils.isBlank(requestDto.getTotal_fee()))
            throw new IllegalArgumentException("WeChat Request params total_fee is null");
        if (StringUtils.isBlank(requestDto.getSpbill_create_ip()))
            throw new IllegalArgumentException("WeChat Request params spbill_create_ip is null");
        if (StringUtils.isBlank(requestDto.getNotify_url()))
            throw new IllegalArgumentException("WeChat Request params notify_url is null");
        if (StringUtils.isBlank(requestDto.getTrade_type()))
            throw new IllegalArgumentException("WeChat Request params trade_type is null");
        if (requestDto.getTrade_type().equals("JSAPI") && StringUtils.isBlank(requestDto.getOpenid()))
            throw new IllegalArgumentException("WeChat Request params openid is null");
        if (requestDto.getTrade_type().equals("NATIVE") && StringUtils.isBlank(requestDto.getProduct_id()))
            throw new IllegalArgumentException("WeChat Request params product_id is null");


        if (requestDto.getBody().length() > 32)
            throw new IllegalArgumentException("WeChat Request params body is null");
        if (requestDto.getOut_trade_no().length() > 32)
            throw new IllegalArgumentException("WeChat Request params out_trade_no is null");
        if (requestDto.getTotal_fee().length() > 32)
            throw new IllegalArgumentException("WeChat Request params total_fee is null");
        if (StringUtils.isBlank(requestDto.getSpbill_create_ip()))
            throw new IllegalArgumentException("WeChat Request params spbill_create_ip is null");
        if (StringUtils.isBlank(requestDto.getNotify_url()))
            throw new IllegalArgumentException("WeChat Request params notify_url is null");
        if (StringUtils.isBlank(requestDto.getTrade_type()))
            throw new IllegalArgumentException("WeChat Request params trade_type is null");
        if (requestDto.getTrade_type().equals("JSAPI") && StringUtils.isBlank(requestDto.getOpenid()))
            throw new IllegalArgumentException("WeChat Request params openid is null");
        if (requestDto.getTrade_type().equals("NATIVE") && StringUtils.isBlank(requestDto.getProduct_id()))
            throw new IllegalArgumentException("WeChat Request params product_id is null");
    }

}
