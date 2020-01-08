package com.immoc.sercurity.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

    private BufferedImage iamge;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage iamge, String code, LocalDateTime expireTime) {
        this.iamge = iamge;
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode(BufferedImage iamge, String code, int expireIn) {
        this.iamge = iamge;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public BufferedImage getIamge() {
        return iamge;
    }

    public void setIamge(BufferedImage iamge) {
        this.iamge = iamge;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
