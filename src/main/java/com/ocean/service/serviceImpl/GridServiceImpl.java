package com.ocean.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ocean.entity.Grid;
import com.ocean.exception.DataNotFoundException;
import com.ocean.mapper.GridMapper;
import com.ocean.service.GridService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GridServiceImpl implements GridService{

    private static final Logger logger = LoggerFactory.getLogger(GridServiceImpl.class);

    @Autowired
    private GridMapper mapper;

    @Override
    public Grid getGrid(Integer id) {

        Grid model = mapper.selectByPrimaryKey(id);
        if (model == null) {
            logger.error("[getGrid]delete Grid id={} fail", id);
            throw new DataNotFoundException("GET data fail");
        }
        return model;

    }

    @Override
    public void save(Grid model) {
        int success = mapper.insert(model);
        if (success <= 0) {
            logger.error("[addGrid]add Grid={} fail",  model.toString());
            throw new DataNotFoundException("Add data fail");
        }
        return;

    }

    @Override
    public void update(Grid model) {
        int success = mapper.update(model);
        if (success <= 0) {
            logger.error("[updateGrid]update Grid={} fail",  model.toString());
            throw new DataNotFoundException("Modify data fail");
        }
        return;

    }

    @Override
    public void del(Integer id) {

        int success = mapper.deleteByPrimaryKey(id);
        if (success <= 0) {
            logger.error("[deleteGrid]delete Grid id={} fail", id);
            throw new DataNotFoundException("Del data fail");
        }
        return;

    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        mapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<Grid> query(int pageNum, int pageSize, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(mapper.query(paramMap));
    }

}