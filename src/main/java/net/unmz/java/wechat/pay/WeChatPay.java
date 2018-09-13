package net.unmz.java.wechat.pay;

import net.unmz.java.util.http.HttpUtils;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.security.MD5Utils;
import net.unmz.java.util.security.SignUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
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

    protected void validateParams(BaseRequestDto dto) {
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
        if (dto.getNonce_str().length() > 32)
            throw new IllegalArgumentException("WeChat Request params nonce_str is too long");
    }

    public static boolean checkSign(String xmlString) {
        Map<String, String> map = null;

        try {
            map = XmlUtils.toMap(xmlString.getBytes(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (map != null) {
            String signFromAPIResponse = map.get("sign");
            if (StringUtils.isBlank(signFromAPIResponse)) {
                System.out.println("API返回的数据签名数据不存在，有可能被第三方篡改");
                return false;
            }
            System.out.println("回调里面的签名是:" + signFromAPIResponse);
            String signStr = SignUtils.getSign(map);//将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
            String signForAPIResponse = MD5Utils.sign(signStr, "&key=" + AppKey, "utf-8").toUpperCase();
            if (!signForAPIResponse.equals(signFromAPIResponse)) {
                System.out.println("API返回的数据签名验证不通过，有可能被第三方篡改 signForAPIResponse生成的签名为 " + signForAPIResponse);
                return false;//签名验不过，表示这个API返回的数据有可能已经被篡改了
            }
            System.out.println("恭喜，API返回的数据签名验证通过");
            return true;
        }
        System.out.println("解析xml为空，数据异常或被篡改");
        return false;
    }
}
