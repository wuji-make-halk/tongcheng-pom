package com.micro.pmo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.micro.pmo.commons.utils.DateTimeUtils;
import com.micro.pmo.web.interceptor.LoginAndminInterceptor;
import com.micro.pmo.web.interceptor.LoginInterceptor;

@Configuration
public class PmoWebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(PmoWebMvcConfig.class);

	@Autowired
	private LoginAndminInterceptor loginAndminInterceptor;
	
	@Autowired
	private LoginInterceptor logInterceptor;

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        /* 是否允许请求带有验证信息 */
        corsConfiguration.setAllowCredentials(true);
        /* 允许访问的客户端域名 */
        corsConfiguration.addAllowedOrigin("*");
        /* 允许服务端访问的客户端请求头 */
        corsConfiguration.addAllowedHeader("*");
        /* 允许访问的方法名,GET POST等 */
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(logInterceptor).addPathPatterns("/api-app/**")
		.excludePathPatterns("/api-app/cus/login","/api-app/cus/sendMsg","/api-app/store/info",
				"/api-app/car/show-info","/api-app/car/cus-car-list","/api-app/store/car-list",
				"/api-app/store/car-info","/api-app/banner/list","/api-app/weixin/authorization",
				"/api-app/weixin/login","/api-app/store/car-page","/api-app/weixin/share-authorize",
				"/api-app/share/store/info","/api-app/share/store/car-list","/api-app/share/store/car-info",
				"/api-app/share/platform/car-list","/api-app/share/platform/car-info","/api-app/car/reserve/jpush-test",
				"/api-app/car/favorite-list","/api-app/brand/alone-list","/api-app/brand/series-list","/api-app/brand/series-infos",
				"/api-app/want","/api-app/appraisal");
		log.info("添加[app]用户拦截器");

		registry.addInterceptor(loginAndminInterceptor).addPathPatterns("/api-pc/**")
				.excludePathPatterns("/api-pc/user/login","/api-pc/car300/insurance-notify",
						"/api-pc/chaboshi/notify","/api-pc/user/login-out","/api-pc/wx/pay/notify/order",
						"/api-pc/wx/pay/notify/refund");
		log.info("添加[ pc ] 用户拦截器");

		super.addInterceptors(registry);
	}

	/**
	 * 添加消息转换器
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.QuoteFieldNames,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.DisableCircularReferenceDetect);
		/*fastJsonConfig.setSerializeFilters((ValueFilter) (o, s, source) -> {
			if (source == null) {return "";}
			return source;});
			SerializerFeature.WriteEnumUsingToString,
			SerializerFeature.WriteDateUseDateFormat,*/
		fastJsonConfig.setDateFormat(DateTimeUtils.timePattern7);
		//处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);

		log.info("添加FastJSON消息转换器");
	}
	
	/**
	 * 
	 * 方法功能说明：编码过滤器
	 * @return    
	 * @return FilterRegistrationBean
	 *
	 */
	@Bean
    public FilterRegistrationBean configEncodingFilterRegistration() {
		
		CharacterEncodingFilter filter = new org.springframework.web.filter.CharacterEncodingFilter();
		filter.setForceEncoding(true);
		filter.setEncoding("UTF-8");
		
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.addUrlPatterns("/*");
        return registration;
    }
	/***
	 * 上传图片
	 * @return
	 */
    @Bean 
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //允许上传的文件最大值
        factory.setMaxFileSize("10MB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("50MB"); 
        //本地删除
        //factory.setLocation("/www/wwwroot/javaweb");
        return factory.createMultipartConfig();  
    } 

}

