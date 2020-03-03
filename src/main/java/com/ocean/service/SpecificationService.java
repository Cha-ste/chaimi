package com.ocean.service;

import java.util.Map;
import java.util.List;
import com.ocean.entity.Specification;
import com.github.pagehelper.PageInfo;


public interface SpecificationService {

    Specification getSpecification(Integer id);

    void save(Specification model);

    void update(Specification model);

    void del(Integer id);

    PageInfo<Specification> query(int currentPage, int itemsPerPage, Map<String, Object> paramMap);

    void deleteBatch(List<Integer> ids);
}