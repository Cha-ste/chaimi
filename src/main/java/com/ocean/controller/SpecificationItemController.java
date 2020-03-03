package com.ocean.controller;

import com.github.pagehelper.PageInfo;
import com.ocean.aop.BusinessLog;
import com.ocean.entity.SpecificationItem;
import com.ocean.service.SpecificationItemService;
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
import java.util.List;
import java.util.Map;

@RestController("SpecificationItemController")
@RequestMapping("/specificationItem")
@Api(tags = "规格值相关接口")
public class SpecificationItemController {

    public static Logger logger = LoggerFactory.getLogger(SpecificationItemController.class);

    @Autowired
    private SpecificationItemService service;

    @GetMapping(value = "/get")
    @ApiOperation(value = "获取规格值")
    public ResultBean<SpecificationItem> get(@RequestParam Integer id) {
        SpecificationItem entity = service.getSpecificationItem(id);
        return ResultBean.success(entity);
    }

    @GetMapping(value = "query")
    @ApiOperation(value = "搜索规格值")
    public ResultBean<PageInfo<SpecificationItem>> query(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String jsonStr) {
        Map<String, Object> paramMap = JSONUtil.parseMap(jsonStr);
        PageInfo<SpecificationItem> pageInfo = service.query(pageNum, pageSize, paramMap);
        return ResultBean.success(pageInfo);
    }

    @GetMapping("/getListBySpecification")
    @ApiOperation("获取规格下的所有规格值")
    public ResultBean<List<SpecificationItem>> getListBySpecification(@RequestParam Integer specificationId) {
        List<SpecificationItem> list = service.getListBySpecification(specificationId);
        return ResultBean.success(list);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "编辑规格值")
    @BusinessLog("编辑规格值")
    public ResultBean save(SpecificationItem model) {
        if (CommonUtil.isNullOrZero(model.getId())) {
            service.save(model);
        } else {
            service.update(model);
        }

        return ResultBean.success("保存成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除规格值")
    @BusinessLog("删除规格值")
    public ResultBean del(@RequestParam Integer id) {
        service.del(id);
        return ResultBean.success("删除成功");
    }

    @PostMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除")
    @BusinessLog(value = "批量删除规格值")
    public ResultBean deleteBatch(@RequestParam Integer[] ids) {
        service.deleteBatch(Arrays.asList(ids));
        return ResultBean.success("删除成功");
    }

}