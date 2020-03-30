package com.glodon.job.admin.dao;

import com.glodon.job.admin.core.model.GlodonJobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@Mapper
public interface GlodonJobGroupDao {

    public List<GlodonJobGroup> findAll();

    public List<GlodonJobGroup> findByAddressType(@Param("addressType") int addressType);

    public int save(GlodonJobGroup xxlJobGroup);

    public int update(GlodonJobGroup xxlJobGroup);

    public int remove(@Param("id") int id);

    public GlodonJobGroup load(@Param("id") int id);
}
