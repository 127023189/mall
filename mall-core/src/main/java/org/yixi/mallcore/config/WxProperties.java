package org.yixi.mallcore.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "litemall.wx")
@Data
public class WxProperties {

    private String appId;

    private String appSecret;

    private String mchId;

    private String mchKey;

    private String notifyUrl;

    private String keyPath;

    private String token;

    private String aesKey;
}
