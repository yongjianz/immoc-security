package com.immoc.sercurity.core.social.qq.api.connet;


import com.immoc.sercurity.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;


public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String  appScecret) {
        super(providerId, new QQServiceProvider(appId, appScecret), new QQAdapter());
    }
}
