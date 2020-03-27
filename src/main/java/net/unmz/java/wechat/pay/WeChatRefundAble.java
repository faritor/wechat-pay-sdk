package net.unmz.java.wechat.pay;

import net.unmz.java.util.data.DataLengthCheckHelper;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatResponseCodeEnum;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.RefundRequestDto;
import net.unmz.java.wechat.pay.dto.response.RefundQueryResponseDto;
import net.unmz.java.wechat.pay.dto.response.RefundResponseDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付申请退款接口
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/4/2 12:15
 * @since JDK 1.8
 */
public class WeChatRefundAble extends WeChatPay {

    @Override
    public BaseResponseDto execute(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.REFUNDABLE.getUrl());
        RefundResponseDto responseDto = this.handleRefundOrderParams(result);
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
        String result = doPostWeChetRequest(dto, WeChatURLEnum.REFUNDABLE.getUrl());
        return XmlUtils.toMap(result.getBytes(), StandardCharsets.UTF_8.displayName());
    }

    /**
     * 校验必填请求参数
     *
     * @param dto 申请退款实体
     */
    @Override
    protected void validateParams(BaseRequestDto dto) {
        super.validateParams(dto);
        RefundRequestDto requestDto = (RefundRequestDto) dto;
        DataLengthCheckHelper.validateAttributeValueLength(requestDto);
    }

    private RefundResponseDto handleRefundOrderParams(String paramsXmlStr) throws Exception {
        try {
            Map<String, String> data = XmlUtils.toMap(paramsXmlStr.getBytes(), "utf-8");
            RefundResponseDto dto = JsonUtils.toBean(JsonUtils.toJSON(data), RefundResponseDto.class);
//            int refund_count = Integer.parseInt(dto.getRefund_count());
//            List<Map<String, Object>> addPerporties = new ArrayList<>();
//            for (int i = 0; i < refund_count; i++) {
//                Map<String, Object> perporties = new HashMap<>();
//                perporties.put("refund_id_" + i, data.get("refund_id_" + i));
//                perporties.put("refund_fee_" + i, data.get("refund_fee_" + i));
//                perporties.put("out_refund_no_" + i, data.get("out_refund_no_" + i));
//                perporties.put("refund_status_" + i, data.get("refund_status_" + i));
//                perporties.put("refund_channel_" + i, data.get("refund_channel_" + i));
//                perporties.put("settlement_refund_fee_" + i, data.get("settlement_refund_fee_" + i));
//
//                perporties.put("refund_account_" + i, data.get("refund_account_" + i));
//                perporties.put("refund_recv_accout_" + i, data.get("refund_recv_accout_" + i));
//                perporties.put("refund_success_time_" + i, data.get("refund_success_time_" + i));
//
//                addPerporties.add(perporties);
//            }

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeChatException(e.getMessage());
        }
    }
}
