package com.ocean.service;

import com.github.pagehelper.PageInfo;
import com.ocean.entity.Banner;

import java.util.List;
import java.util.Map;


public interface BannerService {

    Banner getBanner(Integer id);

    void save(Banner model);

    void update(Banner model);

    void del(Integer id);

    PageInfo<Banner> query(int currentPage, int itemsPerPage, Map<String, Object> paramMap);

    void deleteBatch(List<Integer> ids);
}