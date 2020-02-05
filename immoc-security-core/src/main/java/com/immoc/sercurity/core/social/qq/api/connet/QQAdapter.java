package com.immoc.sercurity.core.social.qq.api.connet;

import com.immoc.sercurity.core.social.qq.api.QQ;
import com.immoc.sercurity.core.social.qq.api.QQUserinfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserinfo qqUserInfo = api.getQQUserInfo();
        values.setDisplayName(qqUserInfo.getNickname());
        values.setProviderUserId(qqUserInfo.getOpenId());
        values.setImageUrl(qqUserInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);

    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
