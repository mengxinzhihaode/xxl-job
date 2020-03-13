package com.glodon.job.admin.dao;

import com.glodon.job.admin.core.model.GlodonJobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * job info
 * @author xuxueli 2016-1-12 18:03:45
 */
@Mapper
public interface GlodonJobInfoDao {

	public List<GlodonJobInfo> pageList(@Param("offset") int offset,
									 @Param("pagesize") int pagesize,
									 @Param("jobGroup") int jobGroup,
									 @Param("triggerStatus") int triggerStatus,
									 @Param("jobDesc") String jobDesc,
									 @Param("executorHandler") String executorHandler,
									 @Param("author") String author);
	public int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("jobGroup") int jobGroup,
							 @Param("triggerStatus") int triggerStatus,
							 @Param("jobDesc") String jobDesc,
							 @Param("executorHandler") String executorHandler,
							 @Param("author") String author);
	
	public int save(GlodonJobInfo info);

	public GlodonJobInfo loadById(@Param("id") int id);
	
	public int update(GlodonJobInfo xxlJobInfo);
	
	public int delete(@Param("id") long id);

	public List<GlodonJobInfo> getJobsByGroup(@Param("jobGroup") int jobGroup);

	public int findAllCount();

	public List<GlodonJobInfo> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize );

	public int scheduleUpdate(GlodonJobInfo xxlJobInfo);


}
