package com.immoc.sercurity.core.validate.code.image;

import com.immoc.sercurity.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode extends ValidateCode {

    private BufferedImage iamge;

    public ImageCode(BufferedImage iamge, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.iamge = iamge;
    }

    public ImageCode(BufferedImage iamge, String code, int expireIn) {
        super(code, expireIn);
        this.iamge = iamge;
    }

    public BufferedImage getIamge() {
        return iamge;
    }

    public void setIamge(BufferedImage iamge) {
        this.iamge = iamge;
    }


}
