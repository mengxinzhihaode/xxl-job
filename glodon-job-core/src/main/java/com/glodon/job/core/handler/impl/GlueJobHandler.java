package com.glodon.job.core.handler.impl;

import com.glodon.job.core.biz.model.ReturnT;
import com.glodon.job.core.log.GlodonJobLogger;
import com.glodon.job.core.handler.IJobHandler;

/**
 * glue job handler
 *
 * @author xuxueli 2016-5-19 21:05:45
 */
public class GlueJobHandler extends IJobHandler {

	private long glueUpdatetime;
	private IJobHandler jobHandler;
	public GlueJobHandler(IJobHandler jobHandler, long glueUpdatetime) {
		this.jobHandler = jobHandler;
		this.glueUpdatetime = glueUpdatetime;
	}
	public long getGlueUpdatetime() {
		return glueUpdatetime;
	}

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		GlodonJobLogger.log("----------- glue.version:"+ glueUpdatetime +" -----------");
		return jobHandler.execute(param);
	}

}
