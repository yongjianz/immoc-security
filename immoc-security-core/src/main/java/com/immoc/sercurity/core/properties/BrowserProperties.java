package com.immoc.sercurity.core.properties;

public class BrowserProperties {

    private String loginPage = "/signIn.html";

    private LoginType loginType =LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
