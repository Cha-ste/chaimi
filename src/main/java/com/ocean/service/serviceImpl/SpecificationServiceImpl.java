package com.ocean.service.serviceImpl;

import com.ocean.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import com.ocean.entity.Specification;
import com.ocean.service.SpecificationService;
import com.ocean.mapper.SpecificationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SpecificationServiceImpl implements SpecificationService{

    private static final Logger logger = LoggerFactory.getLogger(SpecificationServiceImpl.class);

    @Autowired
    private SpecificationMapper mapper;

    @Override
    public Specification getSpecification(Integer id) {

        Specification model = mapper.selectByPrimaryKey(id);
        if (model == null) {
            logger.error("[getSpecification]delete Specification id={} fail", id);
            throw new DataNotFoundException("GET data fail");
        }
        return model;

    }

    @Override
    public void save(Specification model) {
        int success = mapper.insert(model);
        if (success <= 0) {
            logger.error("[addSpecification]add Specification={} fail",  model.toString());
            throw new DataNotFoundException("Add data fail");
        }
        return;

    }

    @Override
    public void update(Specification model) {
        int success = mapper.updateByPrimaryKey(model);
        if (success <= 0) {
            logger.error("[updateSpecification]update Specification={} fail",  model.toString());
            throw new DataNotFoundException("Modify data fail");
        }
        return;

    }

    @Override
    public void del(Integer id) {

        int success = mapper.deleteByPrimaryKey(id);
        if (success <= 0) {
            logger.error("[deleteSpecification]delete Specification id={} fail", id);
            throw new DataNotFoundException("Del data fail");
        }
        return;

    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        mapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<Specification> query(int pageNum, int pageSize, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(mapper.query(paramMap));
    }

}