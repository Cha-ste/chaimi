package com.ocean.controller;

import com.github.pagehelper.PageInfo;
import com.ocean.aop.BusinessLog;
import com.ocean.entity.Banner;
import com.ocean.service.BannerService;
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

@RestController("BannerController")
@RequestMapping("/banner")
@Api(tags = "Banner相关接口")
public class BannerController {

    public static Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private BannerService service;

    @GetMapping(value = "/get")
    @ApiOperation(value = "获取banner详情")
    public ResultBean<Banner> get(@RequestParam Integer id) {
        Banner entity = service.getBanner(id);
        return ResultBean.success(entity);
    }

    @GetMapping(value = "query")
    @ApiOperation(value = "搜索banner")
    public ResultBean<PageInfo<Banner>> query(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String jsonStr) {
        Map<String, Object> paramMap = JSONUtil.parseMap(jsonStr);

        PageInfo<Banner> pageInfo = service.query(pageNum, pageSize, paramMap);
        return ResultBean.success(pageInfo);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "新增、修改banner")
    @BusinessLog("编辑banner")
    public ResultBean save(Banner model) {
        if (CommonUtil.isNullOrZero(model.getId())) {
            service.save(model);
        } else {
            service.update(model);
        }
        return ResultBean.success("保存成功");
    }

    @ApiOperation(value = "删除banner")
    @BusinessLog("删除banner")
    @RequestMapping(value = "/delete")
    public ResultBean del(Integer id) {
        service.del(id);
        return ResultBean.success("删除成功");
    }

    @PostMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除banner")
    @BusinessLog("批量删除banner")
    public ResultBean deleteBatch(Integer[] ids) {
        service.deleteBatch(Arrays.asList(ids));
        return ResultBean.success("删除成功");
    }

}