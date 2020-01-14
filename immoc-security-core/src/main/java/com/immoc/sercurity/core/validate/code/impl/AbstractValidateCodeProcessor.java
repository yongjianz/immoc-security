package com.immoc.sercurity.core.validate.code.impl;

import com.immoc.sercurity.core.validate.code.*;
import com.immoc.sercurity.core.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
        String type = getValidateProcessType().toString().toLowerCase();
        String generatorName = type+StringUtils.substringAfter(ValidateCodeGenerator.class.getSimpleName(),"Validate");
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 获取验证码类型
     * */
    private ValidateCodeType getValidateProcessType(){
         String type = StringUtils.substringBefore(getClass().getSimpleName(),"CodeProcessor");
         return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
    * 保存验证码
    * */
    private void save(ServletWebRequest request, C validateCode){
        sessionStrategy.setAttribute(request,getSessionKey(),validateCode);
    }

    /**
     * 构建验证码放入session时的key
     */
    private String getSessionKey(){
        return SESSION_KEY_PREFIX+getValidateProcessType().toString();
    }
    protected abstract void send(ServletWebRequest request, C validateCode) throws IOException, ServletRequestBindingException;

    public void validate(ServletWebRequest request) throws ServletRequestBindingException {
        C codeInSession = (C) sessionStrategy.getAttribute(request,getSessionKey());
        ValidateCodeType validateCodeType = getValidateProcessType();
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),validateCodeType.getParmNameOnvalidate());

        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException(validateCodeType+" 验证码不能为空");
        }

        if(codeInSession==null){
            throw new ValidateCodeException(validateCodeType+" 验证码不存在");
        }

        if (codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request, getSessionKey());
            throw new ValidateCodeException(validateCodeType+" 验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)){
            throw new ValidateCodeException(validateCodeType+" 验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, getSessionKey());
    }
}
