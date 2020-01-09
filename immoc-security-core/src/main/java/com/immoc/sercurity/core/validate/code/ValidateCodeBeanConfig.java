package com.immoc.sercurity.core.validate.code;

import com.immoc.sercurity.core.properties.SecurityProperties;
import com.immoc.sercurity.core.validate.code.image.ImageCodeGenerator;
import com.immoc.sercurity.core.validate.code.sms.DefaultSmsCodeSender;
import com.immoc.sercurity.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator generator = new ImageCodeGenerator();
        generator.setSecurityProperties(securityProperties);
        return generator;
    }

    @Bean
    @ConditionalOnMissingBean(name="smsCodeSender")
    public SmsCodeSender smsCodeSender(){
        DefaultSmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
        return smsCodeSender;
    }
}
