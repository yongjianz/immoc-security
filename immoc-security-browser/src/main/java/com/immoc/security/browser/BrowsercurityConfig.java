package com.immoc.security.browser;

import com.immoc.security.browser.authentication.myAuthenticationSuccessHandler;
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
    private myAuthenticationSuccessHandler successHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.formLogin()//表单登录
                .loginPage("/authentication/require")//设置登录页面
                .loginProcessingUrl("/authentication/form")//修改UsernamePasswordAuthenticationFilter默认处理的登录url
                .successHandler(successHandler)
                .and() //所有请求都需要登录认证
                .authorizeRequests()
                .antMatchers("/code/image","/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()//指定页面无需认证
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
