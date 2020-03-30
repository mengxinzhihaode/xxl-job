package com.glodon.job.admin.dao;

import com.glodon.job.admin.core.model.GlodonJobLogGlue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * job log for glue
 * @author xuxueli 2016-5-19 18:04:56
 */
@Mapper
public interface GlodonJobLogGlueDao {
	
	public int save(GlodonJobLogGlue xxlJobLogGlue);
	
	public List<GlodonJobLogGlue> findByJobId(@Param("jobId") int jobId);

	public int removeOld(@Param("jobId") int jobId, @Param("limit") int limit);

	public int deleteByJobId(@Param("jobId") int jobId);
	
}
