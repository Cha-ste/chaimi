package com.ocean.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ocean.entity.Category;
import com.github.pagehelper.PageInfo;


public interface CategoryService {

    Category getCategory(Integer id);

    void save(Category model);

    void update(Category model);

    void del(Integer id);

    PageInfo<Category> query(int currentPage, int itemsPerPage, Map<String, Object> map);

    void deleteBatch(List<Integer> ids);

    List<Category> getChild(Integer parent);

    Boolean hasChild(Integer parent);
}