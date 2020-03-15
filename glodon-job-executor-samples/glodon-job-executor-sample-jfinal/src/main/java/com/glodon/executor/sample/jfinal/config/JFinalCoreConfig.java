package com.glodon.executor.sample.jfinal.config;

import com.glodon.executor.sample.jfinal.jobhandler.CommandJobHandler;
import com.glodon.executor.sample.jfinal.jobhandler.DemoJobHandler;
import com.glodon.executor.sample.jfinal.jobhandler.HttpJobHandler;
import com.glodon.executor.sample.jfinal.jobhandler.ShardingJobHandler;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.glodon.executor.sample.jfinal.controller.IndexController;
import com.glodon.job.core.executor.GlodonJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuxueli 2017-08-11 14:17:41
 */
public class JFinalCoreConfig extends JFinalConfig {
	private Logger logger = LoggerFactory.getLogger(JFinalCoreConfig.class);

	// ---------------------- xxl-job executor ----------------------
	private GlodonJobExecutor glodonJobExecutor = null;
	private void initXxlJobExecutor() {

		// registry jobhandler
		GlodonJobExecutor.registJobHandler("demoJobHandler", new DemoJobHandler());
		GlodonJobExecutor.registJobHandler("shardingJobHandler", new ShardingJobHandler());
		GlodonJobExecutor.registJobHandler("httpJobHandler", new HttpJobHandler());
		GlodonJobExecutor.registJobHandler("commandJobHandler", new CommandJobHandler());

		// load executor prop
		Prop xxlJobProp = PropKit.use("glodon-job-executor.properties");

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
	private void destoryXxlJobExecutor() {
		if (glodonJobExecutor != null) {
			glodonJobExecutor.destroy();
		}
	}

	// ---------------------- jfinal ----------------------

	public void configRoute(Routes route) {
		route.add("/", IndexController.class);
	}

	@Override
	public void afterJFinalStart() {
		initXxlJobExecutor();
	}

	@Override
	public void beforeJFinalStop() {
		destoryXxlJobExecutor();
	}

	public void configConstant(Constants constants) {

	}

	public void configPlugin(Plugins plugins) {

	}

	public void configInterceptor(Interceptors interceptors) {

	}

	public void configHandler(Handlers handlers) {

	}


}
