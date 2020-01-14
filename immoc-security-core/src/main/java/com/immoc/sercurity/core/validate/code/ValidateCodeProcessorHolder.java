package com.immoc.sercurity.core.validate.code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType validateCodeType){
        return findValidateCodeProcessor(validateCodeType.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String processorName = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorMap.get(processorName);
        if(validateCodeProcessor == null){
            throw new ValidateCodeException(type+" 验证码处理器不存在");
        }
            return validateCodeProcessor;
    }
}
