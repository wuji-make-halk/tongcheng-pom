package com.micro.pmo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @类功能说明：应用程序入口
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称:
 * @作者：raoBo
 * @创建时间：2019/6/25
 * @版本：V1.0
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableCaching
@MapperScan({"com.micro.pmo.mapper"})
@EnableTransactionManagement 
public class AppPmo {

	private static final Logger log = LoggerFactory.getLogger(AppPmo.class);

	public static void main(String[] args) throws Exception {

		SpringApplication.run(AppPmo.class, args);
		
		log.info("应用启动完成");
	}

}
