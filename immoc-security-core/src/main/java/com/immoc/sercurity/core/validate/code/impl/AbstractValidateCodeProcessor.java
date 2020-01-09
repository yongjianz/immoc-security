package com.immoc.sercurity.core.validate.code.impl;

import com.immoc.sercurity.core.validate.code.ValidateCode;
import com.immoc.sercurity.core.validate.code.ValidateCodeGenerator;
import com.immoc.sercurity.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    private C generate(ServletWebRequest request){
        String type = getValidateProcessType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type+"CodeGenerator");
        return (C) validateCodeGenerator.generate(request);
    }

    private String getValidateProcessType(ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

    protected void save(ServletWebRequest request, C validateCode){
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX+getValidateProcessType(request).toUpperCase(),validateCode);
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws IOException, ServletRequestBindingException;

}
