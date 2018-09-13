### wechat-pay-sdk
封装微信支付，使支付更简单，更专注业务

本项目采用JDK 1.7编译，可以更好的兼容以前的老旧项目。

业余时间整理，不足之处还望指出

maven项目引用地址
```
<dependency>
  <groupId>net.unmz.java.wechat.pay</groupId>
  <artifactId>wechat-pay-sdk</artifactId>
  <version>1.0.3</version>
</dependency>
```

目前实现的功能有:
    
    1.统一下单
    2.查询订单
    3.关闭订单
    4.回调通知

版本更新日志:

1.0.3

    修复回调时,签名未校验通过返回成功消息

1.0.2
    
    新增微信回调解析