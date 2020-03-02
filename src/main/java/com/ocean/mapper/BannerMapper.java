package com.ocean.mapper;

import com.ocean.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BannerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    Banner selectByPrimaryKey(Integer id);

    int update(Banner record);

    List<Banner> query(@Param("map") Map<String, Object> paramMap);

    void deleteBatch(@Param("ids") List<Integer> ids);
}