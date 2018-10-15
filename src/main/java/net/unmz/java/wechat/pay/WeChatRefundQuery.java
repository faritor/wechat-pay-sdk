package net.unmz.java.wechat.pay;

import net.unmz.java.util.data.DataLengthCheckHelper;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatResponseCodeEnum;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.RefundQueryRequestDto;
import net.unmz.java.wechat.pay.dto.response.RefundQueryResponseDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付退款查询接口
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/4/2 12:17
 * @since JDK 1.8
 */
public class WeChatRefundQuery extends WeChatPay {

    @Override
    public BaseResponseDto execute(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.REFUND_QUERY.getUrl());
        RefundQueryResponseDto responseDto = JsonUtils.toBean(XmlUtils.toString(result, "utf-8"), RefundQueryResponseDto.class);
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
     * @param dto 申请退款实体
     */
    @Override
    protected void validateParams(BaseRequestDto dto) {
        super.validateParams(dto);
        RefundQueryRequestDto requestDto = (RefundQueryRequestDto) dto;

        if (StringUtils.isBlank(requestDto.getTransaction_id()) && StringUtils.isBlank(requestDto.getOut_trade_no())
                && StringUtils.isBlank(requestDto.getOut_refund_no()) && StringUtils.isBlank(requestDto.getRefund_id()))
            throw new IllegalArgumentException("WeChat Request params transaction_id or out_trade_no or out_refund_no or refund_id is null");

        DataLengthCheckHelper.validateAttributeValueLength(requestDto);
    }
}
