package com.glodon.job.admin.client;

import com.glodon.job.admin.condition.runtime.SingleModeCondition;
import com.glodon.job.core.executor.impl.GlodonJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional({SingleModeCondition.class})
public class ExecutorConfig {
    private Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);


    @Value("${glodon.job.admin.addresses}")
    private String adminAddress;

    @Bean
    public GlodonJobSpringExecutor achieveExecutor() {
        logger.info(">>>>>>>>>>> Executor config init.");
        GlodonJobSpringExecutor achieveExecutor = new GlodonJobSpringExecutor();
        achieveExecutor.setAdminAddresses(adminAddress);
        achieveExecutor.setAppName("achieveExecutor");
        achieveExecutor.setPort(9997);
        achieveExecutor.setLogRetentionDays(30);
        return achieveExecutor;
    }


    @Bean
    public GlodonJobSpringExecutor ctrlExecutor() {
        logger.info(">>>>>>>>>>> Executor config init.");
        GlodonJobSpringExecutor ctrlExecutor = new GlodonJobSpringExecutor();
        ctrlExecutor.setAdminAddresses(adminAddress);
        ctrlExecutor.setAppName("ctrlExecutor");
        ctrlExecutor.setPort(9994);
        ctrlExecutor.setLogRetentionDays(30);
        return ctrlExecutor;
    }


    @Bean
    public GlodonJobSpringExecutor tradingCenterExecutor() {
        logger.info(">>>>>>>>>>> Executor config init.");
        GlodonJobSpringExecutor tradingCenterExecutor = new GlodonJobSpringExecutor();
        tradingCenterExecutor.setAdminAddresses(adminAddress);
        tradingCenterExecutor.setAppName("tradingCenterExecutor");
        tradingCenterExecutor.setPort(9991);
        tradingCenterExecutor.setLogRetentionDays(30);
        return tradingCenterExecutor;
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