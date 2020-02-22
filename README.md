### wechat-pay-sdk
__封装微信支付，使支付更简单，更专注业务__

本项目采用JDK 1.7编译，可以更好的兼容以前的老旧项目。

业余时间整理，不足之处还望指出

已有功能,可以在测试类中查看示例

maven项目引用地址
```
<dependency>
  <groupId>net.unmz.java.wechat.pay</groupId>
  <artifactId>wechat-pay-sdk</artifactId>
  <version>1.0.9</version>
</dependency>
```

### 目前实现的功能有:(普通商户/服务商)
    
    1.统一下单
    2.查询订单
    3.关闭订单
    4.回调通知
    5.退款查询
    6.申请退款

### 代码示例
#### 统一下单

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

    //dto.setSub_appid("特约商户APPID");
    //dto.setSub_mch_id("特约商户号");
    //dto.setSub_openid("特约商户OpenId");
    
    WeChatPay client = new WeChatUnifiedOrder();
    try {
        WeChatPay.setAppKey("商户密钥");//当选择服务商模式时,此处的appKey选用服务商统一秘钥
        UnifiedOrderResponseDto responseDto = (UnifiedOrderResponseDto) client.execute(dto);
        System.out.println(responseDto.getPrepay_id());
    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

#### 查询订单

    OrderQueryRequestDto dto = new OrderQueryRequestDto();
    dto.setAppid("AppId");
    dto.setMch_id("商户号");
    dto.setNonce_str(StrCodeUtils.getStrCode(16));
    dto.setOut_trade_no("111");
    
    WeChatPay client = new WeChatOrderQuery();
    try {
        WeChatPay.setAppKey("商户密钥");
        OrderQueryResponseDto responseDto = (OrderQueryResponseDto) client.execute(dto);
        System.out.println(responseDto.getTrade_state_desc());
    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

#### 关闭订单

    CloseOrderRequestDto dto = new CloseOrderRequestDto();
    dto.setAppid("AppId");
    dto.setMch_id("商户号");
    dto.setNonce_str(StrCodeUtils.getStrCode(16));
    dto.setOut_trade_no("1231");
    
    //dto.setSub_appid("特约商户APPID");
    //dto.setSub_mch_id("特约商户号");
    
    WeChatPay client = new WeChatCloseOrder();
    try {
        WeChatPay.setAppKey("商户密钥");
        CloseOrderResponseDto responseDto = (CloseOrderResponseDto) client.execute(dto);
        System.out.println(responseDto.getReturn_msg());
    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

#### 回调解析

    HttpServletRequest request = null;
    
    WeChatPay.setAppKey("商户秘钥");
    WeChatCallBackDto dto = WeChatCallBack.callBack(request);
    String result = dto.getResult_wecaht_message();//用于响应给微信,告知微信成功或者失败
    if ("success".equalsIgnoreCase(dto.getReturn_code())) {
        //成功后的业务逻辑
    }

#### 退款订单查询

    RefundQueryRequestDto dto = new RefundQueryRequestDto();
    dto.setAppid("AppId");
    dto.setMch_id("商户号");
    dto.setNonce_str(StrCodeUtils.getStrCode(16));
    dto.setOut_trade_no("1231");

    WeChatPay client = new WeChatCloseOrder();
    try {
        WeChatPay.setAppKey("商户密钥");
        RefundQueryResponseDto responseDto = (RefundQueryResponseDto) client.execute(dto);
        System.out.println(responseDto.getReturn_msg());
    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

#### 申请退款

    RefundRequestDto dto = new RefundRequestDto();
    dto.setAppid("AppId");
    dto.setMch_id("商户号");
    dto.setNonce_str(StrCodeUtils.getStrCode(16));
    dto.setOut_trade_no("1231");

    WeChatPay client = new WeChatCloseOrder();
    try {
        WeChatPay.setAppKey("商户密钥");
        RefundResponseDto responseDto = (RefundResponseDto) client.execute(dto);
        System.out.println(responseDto.getReturn_msg());
    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

### 版本更新日志:

#### 1.0.11

    更新common工具类版本,优化xml解析

#### 1.0.10

    修复bug

#### 1.0.9
    
    1.调整异常输出,将错误码与错误信息一并返回,用英文逗号隔开(修改微信查询订单对订单的描述)
    2.将应用的common依赖版本升级

#### 1.0.8

    新增自定义注解,校验入参中属性的合法性
    问题:在汉字校验中有异常,微信校验中,一个汉字不是一个长度


#### 1.0.7

    修复解析XML文件出现XXE漏洞的风险


#### 1.0.6

    1.更新统一下单时,参数校验的方法
    2.完善文档,方便自己,也方便他人


#### 1.0.5

    补充1.0.4版本中未完善的功能,因已经发布maven中央仓库,所以只能重新发版


#### 1.0.4
    
    1.对接服务商接口配置
    2.完善退款查询与申请退款接口

#### 1.0.3

    修复回调时,签名未校验通过返回成功消息

#### 1.0.2
    
    新增微信回调解析