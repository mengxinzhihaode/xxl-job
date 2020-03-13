package com.glodon.job.executor.core.config;

import com.glodon.job.core.executor.impl.GlodonJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlodonJobConfig {
    private Logger logger = LoggerFactory.getLogger(GlodonJobConfig.class);

    @Value("${glodon.job.admin.addresses}")
    private String adminAddresses;

    @Value("${glodon.job.executor.appname}")
    private String appName;

    @Value("${glodon.job.executor.ip}")
    private String ip;

    @Value("${glodon.job.executor.port}")
    private int port;

    @Value("${glodon.job.accessToken}")
    private String accessToken;

    @Value("${glodon.job.executor.logpath}")
    private String logPath;

    @Value("${glodon.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean
    public GlodonJobSpringExecutor glodonJobExecutor() {
        logger.info(">>>>>>>>>>> glodon-job config init.");
        GlodonJobSpringExecutor xxlJobSpringExecutor = new GlodonJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     *      1、引入依赖：
     *          <dependency>
     *             <groupId>org.springframework.cloud</groupId>
     *             <artifactId>spring-cloud-commons</artifactId>
     *             <version>${version}</version>
     *         </dependency>
     *
     *      2、配置文件，或者容器启动变量
     *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *
     *      3、获取IP
     *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */


}