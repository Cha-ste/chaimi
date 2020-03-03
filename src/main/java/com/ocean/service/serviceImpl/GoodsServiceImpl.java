package com.ocean.service.serviceImpl;

import com.ocean.enums.PutawayEnum;
import com.ocean.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import com.ocean.entity.Goods;
import com.ocean.service.GoodsService;
import com.ocean.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GoodsServiceImpl implements GoodsService{

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper mapper;

    @Override
    public Goods getGoods(Integer id) {

        Goods model = mapper.selectByPrimaryKey(id);
        if (model == null) {
            logger.error("[getGoods]delete Goods id={} fail", id);
            throw new DataNotFoundException("GET data fail");
        }
        return model;

    }

    @Override
    public void save(Goods model) {
        int success = mapper.insert(model);
        if (success <= 0) {
            logger.error("[addGoods]add Goods={} fail",  model.toString());
            throw new DataNotFoundException("Add data fail");
        }
        return;

    }

    @Override
    public void update(Goods model) {
        int success = mapper.updateByPrimaryKey(model);
        if (success <= 0) {
            logger.error("[updateGoods]update Goods={} fail",  model.toString());
            throw new DataNotFoundException("Modify data fail");
        }
        return;

    }

    @Override
    public void del(Integer id) {

        int success = mapper.deleteByPrimaryKey(id);
        if (success <= 0) {
            logger.error("[deleteGoods]delete Goods id={} fail", id);
            throw new DataNotFoundException("Del data fail");
        }
        return;

    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        mapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<Goods> query(int pageNum, int pageSize, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(mapper.query(paramMap));
    }

    @Override
    public void putaway(Integer id, PutawayEnum value) {
        int success = mapper.putaway(id, value.getValue());
        if(success == 0) {
            throw new DataNotFoundException("数据不存在");
        }
    }
}