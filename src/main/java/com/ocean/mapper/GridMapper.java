package com.ocean.mapper;

import com.ocean.entity.Grid;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GridMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Grid record);

    Grid selectByPrimaryKey(Integer id);

    int update(Grid record);

    List<Grid> query(@Param("map")Map<String, Object> paramMap);

    void deleteBatch(@Param("ids") List<Integer> ids);
}