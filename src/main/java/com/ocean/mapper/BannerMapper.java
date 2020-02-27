package com.ocean.mapper;

import com.ocean.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface BannerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    Banner selectByPrimaryKey(Integer id);

    int update(Banner record);

    List<Banner> query(@Param("map")HashMap<String, Object> paramMap);
}