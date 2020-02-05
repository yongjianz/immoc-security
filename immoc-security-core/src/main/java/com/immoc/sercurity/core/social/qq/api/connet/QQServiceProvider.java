package com.immoc.sercurity.core.social.qq.api.connet;

import com.immoc.sercurity.core.social.qq.api.QQ;
import com.immoc.sercurity.core.social.qq.api.QQimpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;
    //用户授权地址
    private static final String URL_AUTHORIZE ="https://graph.qq.com/oauth2.0/authorize";
    //获取令牌地址
    private static final String URL_ACCESS_TOKEN="https://graph.qq.com/oauth2.0/token";
    public QQServiceProvider(String appId, String appSecret) {
        super(new QQAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }


    @Override
    public QQ getApi(String accessToken) {

        return new QQimpl(accessToken, appId);
    }
}
