package com.ocean.service.serviceImpl;

import com.ocean.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.ocean.entity.Category;
import com.ocean.service.CategoryService;
import com.ocean.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper mapper;

    @Override
    public Category getCategory(Integer id) {

        Category model = mapper.selectByPrimaryKey(id);
        if (model == null) {
            logger.error("[getCategory]delete Category id={} fail", id);
            throw new DataNotFoundException("GET data fail");
        }
        return model;

    }

    @Override
    public void save(Category model) {
        int success = mapper.insert(model);
        if (success <= 0) {
            logger.error("[addCategory]add Category={} fail",  model.toString());
            throw new DataNotFoundException("Add data fail");
        }
        return;

    }

    @Override
    public void update(Category model) {
        int success = mapper.update(model);
        if (success <= 0) {
            logger.error("[updateCategory]update Category={} fail",  model.toString());
            throw new DataNotFoundException("Modify data fail");
        }
        return;

    }

    @Override
    @Transactional
    public void del(Integer id) {
        // 先删除该分类的子类，若有多级子孙分类，递归
        List<Category> child = getChild(id);
        if(child != null && child.size() > 0) {
            for(Category category : child) {
                del(category.getId());
            }
        }else {
            int success = mapper.deleteByPrimaryKey(id);
            if (success <= 0) {
                logger.error("[deleteCategory]delete Category id={} fail", id);
                throw new DataNotFoundException("Del data fail");
            }
            return;
        }
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        mapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<Category> query(int pageNum, int pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(mapper.query(keyword));
    }

    @Override
    public List<Category> getChild(Integer parent) {
        return mapper.getChild(parent);
    }

    @Override
    public Boolean hasChild(Integer parent) {
        List<Category> child = mapper.getChild(parent);
        return child != null && child.size() > 0;
    }
}