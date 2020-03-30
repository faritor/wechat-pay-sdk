package net.unmz.java.wechat.pay;

import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.dto.response.WeChatCallBackDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Project Name:
 * 功能描述： 微信支付回调解析
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/4/9 14:54
 * @since JDK 1.8
 */
public class WeChatCallBack {

    public static WeChatCallBackDto callBack(HttpServletRequest request) throws WeChatException {
        try {
            return getCallBackInfo(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeChatException(e.getMessage());
        }
    }

    private static WeChatCallBackDto getCallBackInfo(HttpServletRequest request) {
        WeChatCallBackDto dto = new WeChatCallBackDto();
        String result_code = "FAIL";
        try {
            String xmlString = XmlUtils.parseRequst(request);
            if (StringUtils.isNotBlank(xmlString)) {
                System.out.println("----接收到的数据如下：---\n" + xmlString);
                if (WeChatPay.checkSign(xmlString)) {
                    dto = XmlUtils.toBean(xmlString, WeChatCallBackDto.class);
                    result_code = dto.getResult_code();
                } else
                    result_code = "签名校验失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dto.setResult_wecaht_message(returnXML(result_code));
        return dto;
    }

    private static String returnXML(String return_code) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}
