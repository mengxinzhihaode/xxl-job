package com.glodon.job.admin.core.conf;

import com.glodon.job.admin.core.scheduler.GlodonJobScheduler;
import com.glodon.job.admin.dao.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * glodon-job config
 *
 * @author xuxueli 2017-04-28
 */

@Component
public class GlodonJobAdminConfig implements InitializingBean, DisposableBean {

    private static GlodonJobAdminConfig adminConfig = null;
    public static GlodonJobAdminConfig getAdminConfig() {
        return adminConfig;
    }


    // ---------------------- XxlJobScheduler ----------------------

    private GlodonJobScheduler glodonJobScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;

        glodonJobScheduler = new GlodonJobScheduler();
        glodonJobScheduler.init();
    }

    @Override
    public void destroy() throws Exception {
        glodonJobScheduler.destroy();
    }


    // ---------------------- XxlJobScheduler ----------------------

    // conf
    @Value("${xxl.job.i18n}")
    private String i18n;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${spring.mail.username}")
    private String emailUserName;

    @Value("${xxl.job.triggerpool.fast.max}")
    private int triggerPoolFastMax;

    @Value("${xxl.job.triggerpool.slow.max}")
    private int triggerPoolSlowMax;

    @Value("${xxl.job.logretentiondays}")
    private int logretentiondays;

    // dao, service

    @Resource
    private GlodonJobLogDao glodonJobLogDao;
    @Resource
    private GlodonJobInfoDao glodonJobInfoDao;
    @Resource
    private GlodonJobRegistryDao glodonJobRegistryDao;
    @Resource
    private GlodonJobGroupDao glodonJobGroupDao;
    @Resource
    private GlodonJobLogReportDao glodonJobLogReportDao;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private DataSource dataSource;


    public String getI18n() {
        return i18n;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getEmailUserName() {
        return emailUserName;
    }

    public int getTriggerPoolFastMax() {
        if (triggerPoolFastMax < 200) {
            return 200;
        }
        return triggerPoolFastMax;
    }

    public int getTriggerPoolSlowMax() {
        if (triggerPoolSlowMax < 100) {
            return 100;
        }
        return triggerPoolSlowMax;
    }

    public int getLogretentiondays() {
        if (logretentiondays < 7) {
            return -1;  // Limit greater than or equal to 7, otherwise close
        }
        return logretentiondays;
    }

    public GlodonJobLogDao getGlodonJobLogDao() {
        return glodonJobLogDao;
    }

    public GlodonJobInfoDao getGlodonJobInfoDao() {
        return glodonJobInfoDao;
    }

    public GlodonJobRegistryDao getGlodonJobRegistryDao() {
        return glodonJobRegistryDao;
    }

    public GlodonJobGroupDao getGlodonJobGroupDao() {
        return glodonJobGroupDao;
    }

    public GlodonJobLogReportDao getGlodonJobLogReportDao() {
        return glodonJobLogReportDao;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

}
