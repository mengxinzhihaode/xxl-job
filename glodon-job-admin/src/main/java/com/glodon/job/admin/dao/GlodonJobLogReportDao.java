package com.glodon.job.admin.dao;

import com.glodon.job.admin.core.model.GlodonJobLogReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * job log
 * @author xuxueli 2019-11-22
 */
@Mapper
public interface GlodonJobLogReportDao {

	public int save(GlodonJobLogReport xxlJobLogReport);

	public int update(GlodonJobLogReport xxlJobLogReport);

	public List<GlodonJobLogReport> queryLogReport(@Param("triggerDayFrom") Date triggerDayFrom,
												   @Param("triggerDayTo") Date triggerDayTo);

	public GlodonJobLogReport queryLogReportTotal();

}
