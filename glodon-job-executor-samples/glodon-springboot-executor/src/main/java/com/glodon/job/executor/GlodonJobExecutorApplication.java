package com.glodon.job.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
public class GlodonJobExecutorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
        SpringApplication.run(GlodonJobExecutorApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GlodonJobExecutorApplication.class);
	}
}