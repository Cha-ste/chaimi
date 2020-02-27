package com.ocean.mapper;

import com.ocean.entity.Logs;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface LogsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Logs record);

    Logs selectByPrimaryKey(Integer id);

    int update(Logs record);

    List<Logs> query(@Param("map")HashMap<String, Object> map);

    void deleteBatch(@Param("ids") List<Integer> ids);
}