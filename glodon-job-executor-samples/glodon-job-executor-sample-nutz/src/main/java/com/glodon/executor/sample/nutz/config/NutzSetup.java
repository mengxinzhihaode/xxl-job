package com.glodon.executor.sample.nutz.config;

import com.glodon.executor.sample.nutz.jobhandler.CommandJobHandler;
import com.glodon.executor.sample.nutz.jobhandler.DemoJobHandler;
import com.glodon.executor.sample.nutz.jobhandler.HttpJobHandler;
import com.glodon.executor.sample.nutz.jobhandler.ShardingJobHandler;
import com.glodon.job.core.executor.GlodonJobExecutor;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * nutz setup
 *
 * @author xuxueli 2017-12-25 17:58:43
 */
public class NutzSetup implements Setup {
	private Logger logger = LoggerFactory.getLogger(NutzSetup.class);

	private GlodonJobExecutor glodonJobExecutor = null;

	@Override
	public void init(NutConfig cfg) {

		// registry jobhandler
		GlodonJobExecutor.registJobHandler("demoJobHandler", new DemoJobHandler());
		GlodonJobExecutor.registJobHandler("shardingJobHandler", new ShardingJobHandler());
		GlodonJobExecutor.registJobHandler("httpJobHandler", new HttpJobHandler());
		GlodonJobExecutor.registJobHandler("commandJobHandler", new CommandJobHandler());

		// load executor prop
		PropertiesProxy xxlJobProp = new PropertiesProxy("glodon-job-executor.properties");

		// init executor
		glodonJobExecutor = new GlodonJobExecutor();
		glodonJobExecutor.setAdminAddresses(xxlJobProp.get("xxl.job.admin.addresses"));
		glodonJobExecutor.setAppName(xxlJobProp.get("xxl.job.executor.appname"));
		glodonJobExecutor.setIp(xxlJobProp.get("xxl.job.executor.ip"));
		glodonJobExecutor.setPort(xxlJobProp.getInt("xxl.job.executor.port"));
		glodonJobExecutor.setAccessToken(xxlJobProp.get("glodon.job.accessToken"));
		glodonJobExecutor.setLogPath(xxlJobProp.get("xxl.job.executor.logpath"));
		glodonJobExecutor.setLogRetentionDays(xxlJobProp.getInt("xxl.job.executor.logretentiondays"));

		// start executor
		try {
			glodonJobExecutor.start();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void destroy(NutConfig cfg) {
		if (glodonJobExecutor != null) {
			glodonJobExecutor.destroy();
		}
	}

}
