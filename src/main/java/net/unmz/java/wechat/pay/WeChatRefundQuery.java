package net.unmz.java.wechat.pay;

import net.unmz.java.util.bean.ClassUtils;
import net.unmz.java.util.data.DataLengthCheckHelper;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.map.MapUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatResponseCodeEnum;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.RefundQueryRequestDto;
import net.unmz.java.wechat.pay.dto.response.RefundQueryResponseDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        RefundQueryResponseDto responseDto = handleRefundOrderParams(result);
        System.out.println("WeChat return message : " + JsonUtils.toJSON(responseDto));
        if (WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getResult_code())
                && WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getReturn_code()))
            return responseDto;
        else if (StringUtils.isNotBlank(responseDto.getErr_code())) {
            throw new WeChatException(responseDto.getErr_code(), responseDto.getErr_code_des());
        }
        throw new WeChatException(responseDto.getReturn_code(), responseDto.getReturn_msg());
    }

    @Override
    public Map<String, String> executeRespMap(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.REFUND_QUERY.getUrl());
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
        RefundQueryRequestDto requestDto = (RefundQueryRequestDto) dto;

        if (StringUtils.isBlank(requestDto.getTransaction_id()) && StringUtils.isBlank(requestDto.getOut_trade_no())
                && StringUtils.isBlank(requestDto.getOut_refund_no()) && StringUtils.isBlank(requestDto.getRefund_id())) {
            throw new IllegalArgumentException("WeChat Request params transaction_id or out_trade_no or out_refund_no or refund_id is null");
        }

        DataLengthCheckHelper.validateAttributeValueLength(requestDto);
    }

    private RefundQueryResponseDto handleRefundOrderParams(String paramsXmlStr) throws Exception {
        try {
            Map<String, String> data = XmlUtils.toMap(paramsXmlStr.getBytes(), "utf-8");
            RefundQueryResponseDto dto = JsonUtils.toBean(JsonUtils.toJSON(data), RefundQueryResponseDto.class);
            int refund_count = Integer.parseInt(dto.getRefund_count());
            HashMap<String, Object> addMap = new HashMap<>();
            HashMap<String, Object> addValMap = new HashMap<>();

            for (int i = 0; i < refund_count; i++) {
                addMap.put("refund_id_" + i, Class.forName("java.lang.String"));
                addValMap.put("refund_id_" + i, data.get("refund_id_" + i));

                addMap.put("refund_fee_" + i, Class.forName("java.lang.String"));
                addValMap.put("refund_fee_" + i, data.get("refund_fee_" + i));

                addMap.put("out_refund_no_" + i, Class.forName("java.lang.String"));
                addValMap.put("out_refund_no_" + i, data.get("out_refund_no_" + i));

                addMap.put("refund_status_" + i, Class.forName("java.lang.String"));
                addValMap.put("refund_status_" + i, data.get("refund_status_" + i));

                addMap.put("refund_channel_" + i, Class.forName("java.lang.String"));
                addValMap.put("refund_channel_" + i, data.get("refund_channel_" + i));

                addMap.put("settlement_refund_fee_" + i, Class.forName("java.lang.String"));
                addValMap.put("settlement_refund_fee_" + i, data.get("settlement_refund_fee_" + i));

                addMap.put("refund_account_" + i, Class.forName("java.lang.String"));
                addValMap.put("refund_account_" + i, data.get("refund_account_" + i));

                addMap.put("refund_recv_accout_" + i, Class.forName("java.lang.String"));
                addValMap.put("refund_recv_accout_" + i, data.get("refund_recv_accout_" + i));

                addMap.put("refund_success_time_" + i, Class.forName("java.lang.String"));
                addValMap.put("refund_success_time_" + i, data.get("refund_success_time_" + i));
            }
            Object obj2 = ClassUtils.dynamicClass(dto, addMap, addValMap);
            System.out.println(JsonUtils.toJSON(obj2));
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeChatException(e.getMessage());
        }
    }
}
