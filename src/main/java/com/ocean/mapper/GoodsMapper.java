package com.ocean.mapper;

import com.ocean.entity.Goods;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

public interface GoodsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Goods record);

    List<Goods> query(@Param("map")Map<String, Object> paramMap);

    void deleteBatch(@Param("ids") List<Integer> ids);

    int putaway(@Param("id") Integer id, @Param("value") String value);
}