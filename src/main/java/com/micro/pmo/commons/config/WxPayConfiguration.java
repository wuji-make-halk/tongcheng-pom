package com.micro.pmo.commons.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
public class WxPayConfiguration {

  @Autowired
  private WxPayProperties properties;

  @Bean
  public WxPayConfig wxService() {
    WxPayConfig payConfig = new WxPayConfig();
    //不适用自动配置APPid，在Service中指定适用JSAPIAPPID或APPAPPID
    //payConfig.setAppId(StringUtils.trimToNull(this.properties.getAppId()));
    payConfig.setMchId(StringUtils.trimToNull(this.properties.getMchId()));
    payConfig.setMchKey(StringUtils.trimToNull(this.properties.getMchKey()));
    payConfig.setSubAppId(StringUtils.trimToNull(this.properties.getSubAppId()));
    payConfig.setSubMchId(StringUtils.trimToNull(this.properties.getSubMchId()));
    payConfig.setKeyPath(StringUtils.trimToNull(this.properties.getKeyPath()));
    
    return payConfig;
  }

}
