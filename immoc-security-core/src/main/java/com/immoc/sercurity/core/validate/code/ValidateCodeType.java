package com.immoc.sercurity.core.validate.code;

import com.immoc.sercurity.core.properties.SecurityConstants;

public enum ValidateCodeType {

    SMS{
        @Override
        public String getParmNameOnvalidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    IMAGE{
        @Override
        public String getParmNameOnvalidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    public abstract String getParmNameOnvalidate();
}
