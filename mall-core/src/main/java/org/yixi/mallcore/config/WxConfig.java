package org.yixi.mallcore.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxConfig {
    @Autowired
    private WxProperties wxProperties;

    @Bean
    public WxMaConfig wxMpConfig() {
        WxMaDefaultConfigImpl wxMpConfig = new WxMaDefaultConfigImpl();
        wxMpConfig.setAppid(wxProperties.getAppId());
        wxMpConfig.setSecret(wxProperties.getAppSecret());
        wxMpConfig.setToken(wxProperties.getToken());
        wxMpConfig.setAesKey(wxProperties.getAesKey());
        return wxMpConfig;
    }

    /**
     * 小程序配置
     * @param wxMaConfig
     * @return
     */
    @Bean
    public WxMaService  wxMaService(WxMaConfig wxMaConfig){
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxMaConfig);
        return wxMaService;
    }

    /**
     * 微信支付配置
     * @return
     */
    @Bean
    public WxPayConfig wxPayConfig(){
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(wxProperties.getAppId());
        payConfig.setMchId(wxProperties.getMchId());
        payConfig.setMchKey(wxProperties.getMchKey());
        payConfig.setNotifyUrl(wxProperties.getNotifyUrl());
        payConfig.setKeyPath(wxProperties.getKeyPath());
        payConfig.setTradeType("JSAPI");
        payConfig.setSignType("MD5");
        return payConfig;
    }

    /**
     * 微信支付服务
     * @param wxPayConfig
     * @return
     */
    @Bean
    public WxPayService wxPayService(WxPayConfig wxPayConfig){
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;
    }

}
