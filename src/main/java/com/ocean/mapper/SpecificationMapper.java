package com.ocean.mapper;

import com.ocean.entity.Specification;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

public interface SpecificationMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Specification record);

    Specification selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Specification record);

    List<Specification> query(@Param("map")Map<String, Object> paramMap);

    void deleteBatch(@Param("ids") List<Integer> ids);
}