package com.glodon.job.admin.dao;

import com.glodon.job.admin.core.model.GlodonJobUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:44:59
 */
@Mapper
public interface GlodonJobUserDao {

	public List<GlodonJobUser> pageList(@Param("offset") int offset,
										@Param("pagesize") int pagesize,
										@Param("username") String username,
										@Param("role") int role);
	public int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("username") String username,
							 @Param("role") int role);

	public GlodonJobUser loadByUserName(@Param("username") String username);

	public int save(GlodonJobUser xxlJobUser);

	public int update(GlodonJobUser xxlJobUser);
	
	public int delete(@Param("id") int id);

}
