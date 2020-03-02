package com.ocean.service;

import com.github.pagehelper.PageInfo;
import com.ocean.entity.Grid;

import java.util.List;
import java.util.Map;


public interface GridService {

    Grid getGrid(Integer id);

    void save(Grid model);

    void update(Grid model);

    void del(Integer id);

    PageInfo<Grid> query(int currentPage, int itemsPerPage, Map<String, Object> paramMap);

    void deleteBatch(List<Integer> ids);
}