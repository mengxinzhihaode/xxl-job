package com.glodon.executor.sample.jboot.config;

import com.glodon.executor.sample.jboot.jobhandler.DemoJobHandler;
import com.glodon.executor.sample.jboot.jobhandler.ShardingJobHandler;
import com.glodon.executor.sample.jboot.jobhandler.CommandJobHandler;
import com.glodon.executor.sample.jboot.jobhandler.HttpJobHandler;
import com.glodon.job.core.executor.GlodonJobExecutor;
import io.jboot.Jboot;
import io.jboot.core.listener.JbootAppListenerBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JbootConfig extends JbootAppListenerBase {
    private Logger logger = LoggerFactory.getLogger(JbootConfig.class);

    // ---------------------- xxl-job executor ----------------------
    private GlodonJobExecutor glodonJobExecutor = null;

    private void initXxlJobExecutor() {

        // registry jobhandler
        GlodonJobExecutor.registJobHandler("demoJobHandler", new DemoJobHandler());
        GlodonJobExecutor.registJobHandler("shardingJobHandler", new ShardingJobHandler());
        GlodonJobExecutor.registJobHandler("httpJobHandler", new HttpJobHandler());
        GlodonJobExecutor.registJobHandler("commandJobHandler", new CommandJobHandler());

        // init executor
        glodonJobExecutor = new GlodonJobExecutor();
        glodonJobExecutor.setAdminAddresses(Jboot.configValue("glodon.job.admin.addresses"));
        glodonJobExecutor.setAppName(Jboot.configValue("glodon.job.executor.appname"));
        glodonJobExecutor.setIp(Jboot.configValue("glodon.job.executor.ip"));
        glodonJobExecutor.setPort(Integer.valueOf(Jboot.configValue("glodon.job.executor.port")));
        glodonJobExecutor.setAccessToken(Jboot.configValue("glodon.job.accessToken"));
        glodonJobExecutor.setLogPath(Jboot.configValue("glodon.job.executor.logpath"));
        glodonJobExecutor.setLogRetentionDays(Integer.valueOf(Jboot.configValue("glodon.job.executor.logretentiondays")));

        // start executor
        try {
            glodonJobExecutor.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    // ---------------------- jboot ----------------------

    private void destoryXxlJobExecutor() {
        if (glodonJobExecutor != null) {
            glodonJobExecutor.destroy();
        }
    }

    @Override
    public void onStart() {
        initXxlJobExecutor();
        super.onStart();
    }

    @Override
    public void onStop() {
        destoryXxlJobExecutor();
        super.onStop();
    }
}
