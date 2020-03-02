package com.ocean.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ocean.entity.Banner;
import com.ocean.exception.DataNotFoundException;
import com.ocean.mapper.BannerMapper;
import com.ocean.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService{

    private static final Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Autowired
    private BannerMapper mapper;

    @Override
    public Banner getBanner(Integer id) {

        Banner model = mapper.selectByPrimaryKey(id);
        if (model == null) {
            logger.error("[getBanner]delete Banner id={} fail", id);
            throw new DataNotFoundException("GET data fail");
        }
        return model;

    }

    @Override
    public void save(Banner model) {
        int success = mapper.insert(model);
        if (success <= 0) {
            logger.error("[addBanner]add Banner={} fail",  model.toString());
            throw new DataNotFoundException("Add data fail");
        }
        return;

    }

    @Override
    public void update(Banner model) {
        int success = mapper.update(model);
        if (success <= 0) {
            logger.error("[updateBanner]update Banner={} fail",  model.toString());
            throw new DataNotFoundException("Modify data fail");
        }
        return;

    }

    @Override
    public void del(Integer id) {

        int success = mapper.deleteByPrimaryKey(id);
        if (success <= 0) {
            logger.error("[deleteBanner]delete Banner id={} fail", id);
            throw new DataNotFoundException("数据不存在");
        }
        return;

    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        mapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<Banner> query(int pageNum, int pageSize, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(mapper.query(paramMap));
    }

}