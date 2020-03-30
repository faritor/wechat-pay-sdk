package net.unmz.java.wechat.pay;

import net.unmz.java.util.data.DataLengthCheckHelper;
import net.unmz.java.util.http.HttpUtils;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.security.AESUtils;
import net.unmz.java.util.security.MD5Utils;
import net.unmz.java.util.security.SignUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Map;

/**
 * Project Name:
 * 功能描述：微信支付业务抽象基类
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-4-1 1:57
 * @since JDK 1.8
 */
public abstract class WeChatPay {

    private static String AppKey;

    private static boolean USE_CERT = false;

    private static String SSL_CERT_PATH;

    private static String SSL_CERT_PASSWORD;

    public static void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public static void setUseCert(boolean useCert) {
        USE_CERT = useCert;
    }

    public static void setSslCertPath(String sslCertPath) {
        SSL_CERT_PATH = sslCertPath;
    }

    public static void setSslCertPassword(String sslCertPassword) {
        SSL_CERT_PASSWORD = sslCertPassword;
    }

    public abstract BaseResponseDto execute(BaseRequestDto dto) throws Exception;

    public abstract Map<String, String> executeRespMap(BaseRequestDto dto) throws Exception;

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
        if (USE_CERT) {
            return doRefund(url, requestXml);
        }
        return HttpUtils.doPost(url, null, null, null, requestXml);
    }

    protected void validateParams(BaseRequestDto dto) {
        if (dto == null)
            throw new IllegalArgumentException("WeChat Request params is null");
        DataLengthCheckHelper.validateAttributeValueLength(dto);
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

    public static String reqInfoDecode(String reqInfo) {
        String APISecret = MD5Utils.convert32(AppKey).toLowerCase();
        return AESUtils.decode(reqInfo, APISecret);
    }

    public static String doRefund(String url, String data) throws Exception {
        //注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //指向你的证书的绝对路径，带着证书去访问
        FileInputStream instream = new FileInputStream(new File(SSL_CERT_PATH));//P12文件目录
        try {
            //下载证书时的密码、默认密码是你的MCHID mch_id
            keyStore.load(instream, SSL_CERT_PASSWORD.toCharArray());//这里写密码
        } finally {
            instream.close();
        }
        //下载证书时的密码、默认密码是你的MCHID mch_id
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, SSL_CERT_PASSWORD.toCharArray())//这里也是写密码的
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);


        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
