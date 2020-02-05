package com.immoc.sercurity.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class ImmocSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;


    public ImmocSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    /***
     *改变{@link SocialAuthenticationFilter}拦截的url
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter =(SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T)filter;
    }
}
