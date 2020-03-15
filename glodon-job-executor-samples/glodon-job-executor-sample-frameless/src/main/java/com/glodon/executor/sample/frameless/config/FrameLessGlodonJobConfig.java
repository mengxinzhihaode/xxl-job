package com.glodon.executor.sample.frameless.config;

import com.glodon.executor.sample.frameless.jobhandler.DemoJobHandler;
import com.glodon.executor.sample.frameless.jobhandler.HttpJobHandler;
import com.glodon.executor.sample.frameless.jobhandler.CommandJobHandler;
import com.glodon.executor.sample.frameless.jobhandler.ShardingJobHandler;
import com.glodon.job.core.executor.GlodonJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author xuxueli 2018-10-31 19:05:43
 */
public class FrameLessGlodonJobConfig {
    private static Logger logger = LoggerFactory.getLogger(FrameLessGlodonJobConfig.class);


    private static FrameLessGlodonJobConfig instance = new FrameLessGlodonJobConfig();
    public static FrameLessGlodonJobConfig getInstance() {
        return instance;
    }


    private GlodonJobExecutor glodonJobExecutor = null;

    /**
     * init
     */
    public void initXxlJobExecutor() {

        // registry jobhandler
        GlodonJobExecutor.registJobHandler("demoJobHandler", new DemoJobHandler());
        GlodonJobExecutor.registJobHandler("shardingJobHandler", new ShardingJobHandler());
        GlodonJobExecutor.registJobHandler("httpJobHandler", new HttpJobHandler());
        GlodonJobExecutor.registJobHandler("commandJobHandler", new CommandJobHandler());

        // load executor prop
        Properties xxlJobProp = loadProperties("glodon-job-executor.properties");


        // init executor
        glodonJobExecutor = new GlodonJobExecutor();
        glodonJobExecutor.setAdminAddresses(xxlJobProp.getProperty("xxl.job.admin.addresses"));
        glodonJobExecutor.setAppName(xxlJobProp.getProperty("xxl.job.executor.appname"));
        glodonJobExecutor.setIp(xxlJobProp.getProperty("xxl.job.executor.ip"));
        glodonJobExecutor.setPort(Integer.valueOf(xxlJobProp.getProperty("xxl.job.executor.port")));
        glodonJobExecutor.setAccessToken(xxlJobProp.getProperty("glodon.job.accessToken"));
        glodonJobExecutor.setLogPath(xxlJobProp.getProperty("xxl.job.executor.logpath"));
        glodonJobExecutor.setLogRetentionDays(Integer.valueOf(xxlJobProp.getProperty("xxl.job.executor.logretentiondays")));

        // start executor
        try {
            glodonJobExecutor.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * destory
     */
    public void destoryXxlJobExecutor() {
        if (glodonJobExecutor != null) {
            glodonJobExecutor.destroy();
        }
    }


    public static Properties loadProperties(String propertyFileName) {
        InputStreamReader in = null;
        try {
            ClassLoader loder = Thread.currentThread().getContextClassLoader();

            in = new InputStreamReader(loder.getResourceAsStream(propertyFileName), "UTF-8");;
            if (in != null) {
                Properties prop = new Properties();
                prop.load(in);
                return prop;
            }
        } catch (IOException e) {
            logger.error("load {} error!", propertyFileName);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("close {} error!", propertyFileName);
                }
            }
        }
        return null;
    }

}
