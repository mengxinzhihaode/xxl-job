package com.glodon.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
public class GlodonJobAdminApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
        SpringApplication.run(GlodonJobAdminApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GlodonJobAdminApplication.class);
	}
}