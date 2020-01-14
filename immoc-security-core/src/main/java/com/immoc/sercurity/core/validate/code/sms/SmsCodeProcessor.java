package com.immoc.sercurity.core.validate.code.sms;

import com.immoc.sercurity.core.validate.code.ValidateCode;
import com.immoc.sercurity.core.validate.code.ValidateCodeProcessor;
import com.immoc.sercurity.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws IOException, ServletRequestBindingException {
        String mobile = ServletRequestUtils.getStringParameter(request.getRequest(),"mobile");
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
