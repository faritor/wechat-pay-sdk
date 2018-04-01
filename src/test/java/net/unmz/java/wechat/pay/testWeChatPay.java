package net.unmz.java.wechat.pay;

import net.unmz.java.util.code.StrCodeUtils;
import net.unmz.java.wechat.pay.dto.request.CloseOrderRequestDto;
import net.unmz.java.wechat.pay.dto.request.OrderQueryRequestDto;
import net.unmz.java.wechat.pay.dto.request.UnifiedOrderRequestDto;
import net.unmz.java.wechat.pay.dto.response.CloseOrderResponseDto;
import net.unmz.java.wechat.pay.dto.response.OrderQueryResponseDto;
import net.unmz.java.wechat.pay.dto.response.UnifiedOrderResponseDto;
import org.junit.Test;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付接口单元测试
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-4-1 12:30
 * @since JDK 1.8
 */

public class testWeChatPay {

    @Test
    public void testUnifiedOrder() {
        UnifiedOrderRequestDto dto = new UnifiedOrderRequestDto();
        dto.setAppid("AppId");
        dto.setMch_id("商户号");
        dto.setBody("123");
        dto.setNonce_str(StrCodeUtils.getStrCode(16));
        dto.setNotify_url("123");
        dto.setOut_trade_no("111");
        dto.setTrade_type("JSAPI");
        dto.setTotal_fee("123");
        dto.setSpbill_create_ip("192.168.1.1");
        dto.setOpenid("OpenId");

        WeChatPay client = new WeChatUnifiedOrder();
        try {
            WeChatPay.setAppKey("商户密钥");
            UnifiedOrderResponseDto responseDto = (UnifiedOrderResponseDto) client.execute(dto);
            System.out.println(responseDto.getPrepay_id());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testOrderQuery() {
        OrderQueryRequestDto dto = new OrderQueryRequestDto();
        dto.setAppid("AppId");
        dto.setMch_id("商户号");
        dto.setNonce_str(StrCodeUtils.getStrCode(16));
        dto.setOut_trade_no("111");

        try {
            WeChatPay.setAppKey("商户密钥");
            WeChatPay client = new WeChatOrderQuery();
            OrderQueryResponseDto responseDto = (OrderQueryResponseDto) client.execute(dto);
            System.out.println(responseDto.getTrade_state_desc());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCloseOrder() {
        CloseOrderRequestDto dto = new CloseOrderRequestDto();
        dto.setAppid("AppId");
        dto.setMch_id("商户号");
        dto.setNonce_str(StrCodeUtils.getStrCode(16));
        dto.setOut_trade_no("1231");

        try {
            WeChatPay.setAppKey("商户密钥");
            WeChatPay client = new WeChatCloseOrder();
            CloseOrderResponseDto responseDto = (CloseOrderResponseDto) client.execute(dto);
            System.out.println(responseDto.getReturn_msg());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
