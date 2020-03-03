package com.ocean.controller;

import com.github.pagehelper.PageInfo;
import com.ocean.aop.BusinessLog;
import com.ocean.entity.Specification;
import com.ocean.service.SpecificationService;
import com.ocean.utils.CommonUtil;
import com.ocean.utils.JSONUtil;
import com.ocean.vo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController("SpecificationController")
@RequestMapping("/Specification")
@Api(tags = "规格相关接口")
public class SpecificationController {

    public static Logger logger = LoggerFactory.getLogger(SpecificationController.class);

    @Autowired
    private SpecificationService service;

    @GetMapping(value = "/get")
    @ApiOperation(value = "获取规格详情")
    public ResultBean<Specification> get(@RequestParam Integer id) {
        Specification entity = service.getSpecification(id);
        return ResultBean.success(entity);
    }

    @GetMapping(value = "query")
    @ApiOperation(value = "搜索规格")
    public ResultBean<PageInfo<Specification>> query(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String jsonStr) {
        Map<String, Object> paramMap = JSONUtil.parseMap(jsonStr);
        PageInfo<Specification> pageInfo = service.query(pageNum, pageSize, paramMap);
        return ResultBean.success(pageInfo);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "编辑规格")
    @BusinessLog(value = "编辑规格")
    public ResultBean save(Specification model) {
        if (CommonUtil.isNullOrZero(model.getId())) {
            service.save(model);
        } else {
            service.update(model);
        }

        return ResultBean.success("保存成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除规格")
    @BusinessLog(value = "删除规格")
    public ResultBean del(@RequestParam Integer id) {
        service.del(id);
        return ResultBean.success("删除成功");
    }

    @PostMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除")
    @BusinessLog(value = "批量删除规格")
    public ResultBean deleteBatch(@RequestParam Integer[] ids) {
        service.deleteBatch(Arrays.asList(ids));
        return ResultBean.success("删除成功");
    }

}