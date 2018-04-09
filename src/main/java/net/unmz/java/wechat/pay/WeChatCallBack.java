package net.unmz.java.wechat.pay;

import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.dto.response.WeChatCallBackDto;
import net.unmz.java.wechat.pay.exception.WeChatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/4/9 14:54
 * @since JDK 1.8
 */
public class WeChatCallBack {

    public static WeChatCallBackDto callBack(HttpServletRequest request, HttpServletResponse response) throws WeChatException {
        try {
            WeChatCallBackDto dto = new WeChatCallBackDto();
            response.getWriter().append(getCallBackInfo(dto, request));
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeChatException(e.getMessage());
        }
    }

    private static String getCallBackInfo(WeChatCallBackDto dto, HttpServletRequest request) {
        try {
            String xmlString = XmlUtils.parseRequst(request);
            System.out.println("----接收到的数据如下：---\n" + xmlString);
            Map<String, String> map = XmlUtils.toMap(xmlString.getBytes(), "utf-8");
            String result_code = map.get("result_code");
            if (WeChatPay.checkSign(xmlString)) {
                String xml = XmlUtils.toString(xmlString, "utf-8");
                dto = JsonUtils.toBean(xml, WeChatCallBackDto.class);
                return returnXML(result_code);
            } else {
                return returnXML("FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String returnXML(String return_code) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}
