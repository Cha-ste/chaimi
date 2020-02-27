package com.ocean.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ocean.utils.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ocean.entity.Logs;
import com.ocean.service.LogsService;
import com.ocean.vo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.github.pagehelper.PageInfo;

@RestController("LogsController")
@RequestMapping("/logs")
@Api(tags="日志相关接口")
public class LogsController {

    public static Logger logger = LoggerFactory.getLogger(LogsController.class);

    @Autowired
    private LogsService service;

    @GetMapping(value = "/get")
    @ApiOperation(value="获取日志详情")
    public ResultBean<Logs> get(@RequestParam Integer id) {
        Logs entity=service.getLogs(id);
        return ResultBean.success(entity);
    }

    @GetMapping(value = "/query")
    @ApiOperation(value="搜索")
    public ResultBean<PageInfo<Logs>> query(
        @RequestParam(defaultValue = "1") Integer pageNum,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(required = false) String business) {
        HashMap<String, Object> paramMap = new HashMap<>();
        if (!StringUtils.isBlank(business)) {
                paramMap.put("business", business);
        }else{
            paramMap.put("orderBy","id desc");
        }
        PageInfo<Logs> pageInfo = service.query(pageNum, pageSize, paramMap);
        return ResultBean.success(pageInfo);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value="新增日志")
    public ResultBean save(@RequestBody Logs model) {
        service.save(model);

        return ResultBean.success("保存成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value="删除日志")
    public ResultBean del(@RequestParam Integer id) {
        service.del(id);
        return ResultBean.success("删除成功");
    }

    @PostMapping(value = "/deleteBatch")
    @ApiOperation(value="批量删除日志")
    public ResultBean deleteBatch(@RequestParam Integer[] ids) {
        service.deleteBatch(Arrays.asList(ids));
        return ResultBean.success("删除成功");
    }

}