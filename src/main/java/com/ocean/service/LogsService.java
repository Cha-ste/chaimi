package com.ocean.service;

import java.util.HashMap;
import java.util.List;
import com.ocean.entity.Logs;
import com.github.pagehelper.PageInfo;


public interface LogsService {

    Logs getLogs(Integer id);

    void save(Logs model);

    void update(Logs model);

    void del(Integer id);

    void deleteBatch(List<Integer> ids);

    PageInfo<Logs> query(int currentPage, int itemsPerPage, HashMap<String, Object> paramMap);

}