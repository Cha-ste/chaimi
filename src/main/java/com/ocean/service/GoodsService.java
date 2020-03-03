package com.ocean.service;

import java.util.Map;
import java.util.List;
import com.ocean.entity.Goods;
import com.github.pagehelper.PageInfo;
import com.ocean.enums.PutawayEnum;


public interface GoodsService {

    Goods getGoods(Integer id);

    void save(Goods model);

    void update(Goods model);

    void del(Integer id);

    PageInfo<Goods> query(int currentPage, int itemsPerPage, Map<String, Object> paramMap);

    void deleteBatch(List<Integer> ids);

    void putaway(Integer id, PutawayEnum valueOf);
}