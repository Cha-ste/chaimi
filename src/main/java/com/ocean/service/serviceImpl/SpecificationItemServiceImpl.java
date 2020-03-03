package com.ocean.service.serviceImpl;

import com.ocean.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import com.ocean.entity.SpecificationItem;
import com.ocean.service.SpecificationItemService;
import com.ocean.mapper.SpecificationItemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SpecificationItemServiceImpl implements SpecificationItemService{

    private static final Logger logger = LoggerFactory.getLogger(SpecificationItemServiceImpl.class);

    @Autowired
    private SpecificationItemMapper mapper;

    @Override
    public SpecificationItem getSpecificationItem(Integer id) {

        SpecificationItem model = mapper.selectByPrimaryKey(id);
        if (model == null) {
            logger.error("[getSpecificationItem]delete SpecificationItem id={} fail", id);
            throw new DataNotFoundException("GET data fail");
        }
        return model;

    }

    @Override
    public void save(SpecificationItem model) {
        int success = mapper.insert(model);
        if (success <= 0) {
            logger.error("[addSpecificationItem]add SpecificationItem={} fail",  model.toString());
            throw new DataNotFoundException("Add data fail");
        }
        return;

    }

    @Override
    public void update(SpecificationItem model) {
        int success = mapper.updateByPrimaryKey(model);
        if (success <= 0) {
            logger.error("[updateSpecificationItem]update SpecificationItem={} fail",  model.toString());
            throw new DataNotFoundException("Modify data fail");
        }
        return;

    }

    @Override
    public void del(Integer id) {

        int success = mapper.deleteByPrimaryKey(id);
        if (success <= 0) {
            logger.error("[deleteSpecificationItem]delete SpecificationItem id={} fail", id);
            throw new DataNotFoundException("Del data fail");
        }
        return;

    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        mapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<SpecificationItem> query(int pageNum, int pageSize, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(mapper.query(paramMap));
    }

    @Override
    public List<SpecificationItem> getListBySpecification(Integer specificationId) {
        return mapper.getListBySpecification(specificationId);
    }
}