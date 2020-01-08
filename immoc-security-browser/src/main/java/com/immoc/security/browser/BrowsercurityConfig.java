package com.immoc.security.browser;

import com.immoc.security.browser.authentication.MyAuthenticationFailureHandler;
import com.immoc.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.immoc.sercurity.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowsercurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failHandlerHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.formLogin()//设置为表单登录
                .loginPage("/authentication/require")//设置登录页面
                .loginProcessingUrl("/authentication/form")//修改UsernamePasswordAuthenticationFilter默认处理的登录表单提交url
                .successHandler(successHandler)
                .failureHandler(failHandlerHandler)
                .and() //所有请求都需要登录认证
                .authorizeRequests()
                .antMatchers("/code/image","/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()//配置的页面无需认证
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
