package com.ocean.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ocean.entity.Logs;
import com.ocean.exception.DataNotFoundException;
import com.ocean.mapper.LogsMapper;
import com.ocean.service.LogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LogsServiceImpl implements LogsService{

    private static final Logger logger = LoggerFactory.getLogger(LogsServiceImpl.class);

    @Autowired
    private LogsMapper mapper;

    @Override
    public Logs getLogs(Integer id) {

        Logs model = mapper.selectByPrimaryKey(id);
        if (model == null) {
            logger.error("[getLogs]delete Logs id={} fail", id);
            throw new DataNotFoundException("GET data fail");
        }
        return model;

    }

    @Override
    public void save(Logs model) {
        int success = mapper.insert(model);
        if (success <= 0) {
            logger.error("[addLogs]add Logs={} fail",  model.toString());
            throw new DataNotFoundException("Add data fail");
        }
        return;

    }

    @Override
    public void update(Logs model) {
        int success = mapper.update(model);
        if (success <= 0) {
            logger.error("[updateLogs]update Logs={} fail",  model.toString());
            throw new DataNotFoundException("Modify data fail");
        }
        return;

    }

    @Override
    public void del(Integer id) {

        int success = mapper.deleteByPrimaryKey(id);
        if (success <= 0) {
            logger.error("[deleteLogs]delete Logs id={} fail", id);
            throw new DataNotFoundException("Del data fail");
        }
        return;

    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        mapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<Logs> query(int pageNum, int pageSize, HashMap<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(mapper.query(paramMap));
    }

}