package com.ocean.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.ocean.entity.Banner;
import com.ocean.enums.FileType;
import com.ocean.service.BannerService;
import com.ocean.utils.CommonUtil;
import com.ocean.utils.FileUtil;
import com.ocean.utils.StringUtils;
import com.ocean.vo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

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
            @RequestParam(required = false) String param) {
        HashMap<String, Object> paramMap = new HashMap<>();
        if (StringUtils.isBlank(param)) {
            try {
                paramMap = new ObjectMapper().readValue(param, HashMap.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            paramMap.put("orderBy", "id desc");
        }
        PageInfo<Banner> pageInfo = service.query(pageNum, pageSize, paramMap);
        return ResultBean.success(pageInfo);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "新增、修改banner")
    public ResultBean save(@RequestBody Banner model) {
        Banner record = new Banner();
        BeanUtils.copyProperties(model, record);

        if (CommonUtil.isNullOrZero(record.getId())) {
            service.save(record);
        } else {
            service.update(record);
        }
        return ResultBean.success("保存成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除banner")
    public ResultBean del(@RequestParam Integer id) {
        service.del(id);
        return ResultBean.success("删除成功");
    }

    @PostMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除banner")
    public ResultBean deleteBatch(@RequestParam Integer[] ids) {
        service.deleteBatch(Arrays.asList(ids));
        return ResultBean.success("删除成功");
    }

    @PostMapping("/upload")
    @ApiOperation("banner图上传")
    public ResultBean<String> bannerUpload(MultipartFile file) {
        String filePath = FileUtil.uploadFile(file, FileType.IMAGE);
        if(StringUtils.isBlank(filePath)) {
            return ResultBean.ERROR;
        }
        return ResultBean.success(filePath);
    }

}