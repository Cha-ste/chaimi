package com.ocean.mapper;

import com.ocean.entity.SpecificationItem;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

public interface SpecificationItemMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SpecificationItem record);

    SpecificationItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(SpecificationItem record);

    List<SpecificationItem> query(@Param("map")Map<String, Object> paramMap);

    void deleteBatch(@Param("ids") List<Integer> ids);

    List<SpecificationItem> getListBySpecification(Integer specificationId);
}