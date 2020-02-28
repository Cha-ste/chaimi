package com.ocean.controller;

import com.github.pagehelper.PageInfo;
import com.ocean.aop.BusinessLog;
import com.ocean.entity.Category;
import com.ocean.service.CategoryService;
import com.ocean.utils.CommonUtil;
import com.ocean.vo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("CategoryController")
@RequestMapping("/category")
@Api(tags = "分类相关接口")
public class CategoryController {

    public static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService service;

    @GetMapping(value = "/hasChild")
    @ApiOperation(value = "判断分类是否拥有子类")
    public ResultBean<Boolean> getChild(@RequestParam Integer parent) {
        Boolean hasChild = service.hasChild(parent);
        return ResultBean.success(hasChild);
    }

    @GetMapping(value = "/getChildren")
    @ApiOperation(value = "获取分类的子类")
    public ResultBean<List<Category>> getChildren(@RequestParam Integer parent) {
        List<Category> categoryList = service.getChild(parent);
        return ResultBean.success(categoryList);
    }

    @GetMapping(value = "/get")
    @ApiOperation(value = "获取分类详情")
    public ResultBean<Category> get(@RequestParam Integer id) {
        Category entity = service.getCategory(id);
        return ResultBean.success(entity);
    }

    @GetMapping(value = "query")
    @ApiOperation(value = "搜索分类")
    public ResultBean<PageInfo<Category>> query(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {
        PageInfo<Category> pageInfo = service.query(pageNum, pageSize, keyword);
        return ResultBean.success(pageInfo);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "编辑分类")
    @BusinessLog("编辑分类")
    public ResultBean save(@RequestBody Category model) {
        if (CommonUtil.isNullOrZero(model.getId())) {
            service.save(model);
        } else {
            service.update(model);
        }

        return ResultBean.success("保存成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除分类")
    @BusinessLog(value = "删除分类")
    public ResultBean del(@RequestParam Integer id) {
        service.del(id);
        return ResultBean.success("删除成功");
    }

    /*@PostMapping(value = "/deleteBatch")
    @ApiOperation(value="删除分类")
    @BusinessLog(value="删除分类")
    public ResultBean deleteBatch(@RequestParam Integer[] ids) {
        service.deleteBatch(Arrays.asList(ids));
        return ResultBean.success("删除成功");
    }*/

}