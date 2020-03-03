package com.ocean.service;

import java.util.Map;
import java.util.List;
import com.ocean.entity.SpecificationItem;
import com.github.pagehelper.PageInfo;


public interface SpecificationItemService {

    SpecificationItem getSpecificationItem(Integer id);

    void save(SpecificationItem model);

    void update(SpecificationItem model);

    void del(Integer id);

    PageInfo<SpecificationItem> query(int currentPage, int itemsPerPage, Map<String, Object> paramMap);

    void deleteBatch(List<Integer> ids);

    List<SpecificationItem> getListBySpecification(Integer specificationId);
}