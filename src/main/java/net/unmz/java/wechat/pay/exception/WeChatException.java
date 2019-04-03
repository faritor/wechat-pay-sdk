package net.unmz.java.wechat.pay.exception;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-4-1 0:32
 * @since JDK 1.8
 */
public class WeChatException extends Exception {
    private static final long serialVersionUID = -5796790778231589707L;

    public WeChatException(String message) {
        super(message);
    }

    public WeChatException(String code, String message) {
        super(code + "," + message);
    }
}
