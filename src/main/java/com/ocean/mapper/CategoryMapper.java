package com.ocean.mapper;

import com.ocean.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    Category selectByPrimaryKey(Integer id);

    int update(Category record);

    List<Category> query(@Param("map") Map<String, Object> map);

    void deleteBatch(@Param("ids") List<Integer> ids);

    List<Category> getChild(Integer parent);
}