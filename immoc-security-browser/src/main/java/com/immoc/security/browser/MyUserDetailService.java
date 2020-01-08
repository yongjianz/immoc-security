package com.immoc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String passwordEncode = passwordEncoder.encode("123456");
        logger.info("登录用户名："+username);
        logger.info("登录密码："+passwordEncode);
        return new User(username,passwordEncode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
