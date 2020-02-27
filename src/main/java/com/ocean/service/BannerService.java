package com.ocean.service;

import java.util.HashMap;
import java.util.List;
import com.ocean.entity.Banner;
import com.github.pagehelper.PageInfo;


public interface BannerService {

    Banner getBanner(Integer id);

    void save(Banner model);

    void update(Banner model);

    void del(Integer id);

    PageInfo<Banner> query(int currentPage, int itemsPerPage, HashMap<String, Object> paramMap);

}