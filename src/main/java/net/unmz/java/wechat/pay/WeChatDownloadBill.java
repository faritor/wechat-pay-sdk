package net.unmz.java.wechat.pay;

import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付下载对账单接口
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/4/2 12:17
 * @since JDK 1.8
 */
public class WeChatDownloadBill extends WeChatPay {
    @Override
    public BaseResponseDto execute(BaseRequestDto dto) throws Exception {
        return null;
    }

    @Override
    public Map<String, String> executeRespMap(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.DOWNLOAD_BILL.getUrl());
        return XmlUtils.toMap(result.getBytes(), StandardCharsets.UTF_8.displayName());
    }
}
