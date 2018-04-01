package net.unmz.java.wechat.pay.constants;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付接口地址
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:03
 * @since JDK 1.8
 */
public enum WeChatURLEnum {

    UNIFIED_ORDER("https://api.mch.weixin.qq.com/pay/unifiedorder"),//统一下单
    ORDER_QUERY("https://api.mch.weixin.qq.com/pay/orderquery"),//订单查询
    CLOSE_ORDER("https://api.mch.weixin.qq.com/pay/closeorder"),//关闭订单
    REFUNDABLE("https://api.mch.weixin.qq.com/secapi/pay/refund"),//申请退款
    REFUND_QUERY("https://api.mch.weixin.qq.com/pay/refundquery"),//退款查询
    DOWNLOAD_BILL("https://api.mch.weixin.qq.com/pay/downloadbill"),//下载对账单
    DOWNLOAD_FUND_FLOW("https://api.mch.weixin.qq.com/pay/downloadfundflow"),//下载资金账单
    REPORT("https://api.mch.weixin.qq.com/payitil/report"),//交易保障
    QUERY_COMMENT("https://api.mch.weixin.qq.com/billcommentsp/batchquerycomment"),//拉取订单评价数据
    REVOCATION_ORDER("https://api.mch.weixin.qq.com/secapi/pay/reverse"),//撤销订单

    SHORT_URL("https://api.mch.weixin.qq.com/tools/shorturl"),//转换短链接
    AUTH_CODE_TO_OPENID("https://api.mch.weixin.qq.com/tools/authcodetoopenid"),//通过授权码获取用户OpenId

    ;

    WeChatURLEnum(String url) {
        this.url = url;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
