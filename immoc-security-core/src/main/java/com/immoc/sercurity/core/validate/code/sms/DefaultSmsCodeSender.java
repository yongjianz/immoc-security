package com.immoc.sercurity.core.validate.code.sms;
import org.springframework.stereotype.Component;

public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机 "+mobile+"发送 "+code+"验证码");
    }
}
