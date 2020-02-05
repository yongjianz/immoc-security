package com.immoc.sercurity.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "immoc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private VolidateCodeProperties Code = new VolidateCodeProperties();

    private SocialProperties social = new SocialProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public VolidateCodeProperties getCode() {
        return Code;
    }

    public void setCode(VolidateCodeProperties code) {
        Code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
