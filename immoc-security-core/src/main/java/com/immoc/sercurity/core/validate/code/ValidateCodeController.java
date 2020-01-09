package com.immoc.sercurity.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;


@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeGeneratorMap;

    @GetMapping("/code/{type}")
    public void createSmsCoode(ServletWebRequest request, @PathVariable String type) throws Exception {
        System.out.println(validateCodeGeneratorMap.get(type+"CodeProcessor"));
        validateCodeGeneratorMap.get(type+"CodeProcessor").create(request);
    }

}
