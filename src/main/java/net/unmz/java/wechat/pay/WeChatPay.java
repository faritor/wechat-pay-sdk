package net.unmz.java.wechat.pay;

import net.unmz.java.util.http.HttpUtils;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.security.MD5Utils;
import net.unmz.java.util.security.SignUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.CloseOrderRequestDto;
import net.unmz.java.wechat.pay.dto.request.UnifiedOrderRequestDto;
import net.unmz.java.wechat.pay.dto.response.CloseOrderResponseDto;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-4-1 1:57
 * @since JDK 1.8
 */
public abstract class WeChatPay {

    private static String AppKey;

    public static void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public abstract BaseResponseDto execute(BaseRequestDto dto) throws Exception;


    /**
     * 向微信发起统一下单请求
     *
     * @param dto 统一下单请求实体
     * @return
     * @throws Exception
     */
    protected String doPostWeChetRequest(BaseRequestDto dto, String url) throws Exception {
        validateParams(dto);
        Map<String, String> params = (Map<String, String>) JsonUtils.toBean(JsonUtils.toJSON(dto), Map.class);
        params = SignUtils.paraFilter(params);
        String signStr = SignUtils.getSign(params);
        String sign = MD5Utils.sign(signStr, "&key=" + AppKey, "utf-8").toUpperCase();
        params.put("sign", sign);
        String requestXml = XmlUtils.toXml(params);
        return HttpUtils.doPost(url, null, null, null, requestXml);
    }

    protected void validateParams(BaseRequestDto dto){
        if (dto == null)
            throw new IllegalArgumentException("WeChat Request params is null");

        if (StringUtils.isBlank(dto.getAppid()))
            throw new IllegalArgumentException("WeChat Request params appid is null");
        if (StringUtils.isBlank(dto.getMch_id()))
            throw new IllegalArgumentException("WeChat Request params mchId is null");
        if (StringUtils.isBlank(dto.getNonce_str()))
            throw new IllegalArgumentException("WeChat Request params nonce_str is null");

        if (dto.getAppid().length() > 32)
            throw new IllegalArgumentException("WeChat Request params appid is too long");
        if (dto.getMch_id().length() > 32)
            throw new IllegalArgumentException("WeChat Request params mchId is too long");
        if (dto.getNonce_str().length() >32)
            throw new IllegalArgumentException("WeChat Request params nonce_str is too long");
    }
}
