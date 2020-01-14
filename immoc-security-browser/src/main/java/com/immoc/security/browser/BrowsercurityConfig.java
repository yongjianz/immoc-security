package com.immoc.security.browser;

import com.immoc.security.browser.authentication.MyAuthenticationFailureHandler;
import com.immoc.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.immoc.sercurity.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.immoc.sercurity.core.properties.SecurityProperties;
import com.immoc.sercurity.core.validate.code.SmsCodeFilter;
import com.immoc.sercurity.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowsercurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failHandlerHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setFailureHandler(failHandlerHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setFailureHandler(failHandlerHandler);

        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();
            http.formLogin()//设置为表单登录
                .loginPage("/authentication/require")//设置登录页面
                .loginProcessingUrl("/authentication/form")//修改UsernamePasswordAuthenticationFilter默认处理的登录表单提交url
                .successHandler(successHandler)
                .failureHandler(failHandlerHandler)
                .and() //所有请求都需要登录认证
                .authorizeRequests()
                .antMatchers("/authentication/mobile","/code/image","/code/sms","/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()//配置的页面无需认证
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .apply(smsCodeAuthenticationSecurityConfig);
    }
}

