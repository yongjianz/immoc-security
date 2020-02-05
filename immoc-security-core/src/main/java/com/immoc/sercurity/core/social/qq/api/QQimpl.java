package com.immoc.sercurity.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

public class QQimpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL_GET_OPENID ="https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?\n" +
            "oauth_consumer_key=%s&\n" +
            "openid=%s";

    private String appId;

    private String openId;

    public QQimpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        logger.info(result);

        this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");

    }

    @Override
    public QQUserinfo getQQUserInfo() {
        String url = String.format(URL_GET_USERINFO,appId,openId);

        String result = getRestTemplate().getForObject(url, String.class);
        logger.info(result);
        try {
            return objectMapper.readValue(result, QQUserinfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
