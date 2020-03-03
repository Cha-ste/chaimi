package com.ocean.controller;

import com.github.pagehelper.PageInfo;
import com.ocean.aop.BusinessLog;
import com.ocean.entity.Goods;
import com.ocean.enums.PutawayEnum;
import com.ocean.service.GoodsService;
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

@RestController("GoodsController")
@RequestMapping("/goods")
@Api(tags = "商品相关接口")
public class GoodsController {

    public static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService service;

    @GetMapping(value = "/get")
    @ApiOperation(value = "获取商品详情")
    public ResultBean<Goods> get(@RequestParam Integer id) {
        Goods entity = service.getGoods(id);
        return ResultBean.success(entity);
    }

    @GetMapping(value = "query")
    @ApiOperation(value = "搜索商品")
    public ResultBean<PageInfo<Goods>> query(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String jsonStr) {
        Map<String, Object> paramMap = JSONUtil.parseMap(jsonStr);
        PageInfo<Goods> pageInfo = service.query(pageNum, pageSize, paramMap);
        return ResultBean.success(pageInfo);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "编辑商品")
    @BusinessLog("编辑商品")
    /**
     * 存储需要注意的字段
     *
     *
     */
    public ResultBean save(Goods model) {
        if (CommonUtil.isNullOrZero(model.getId())) {
            service.save(model);
        } else {
            service.update(model);
        }

        return ResultBean.success("保存成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除商品")
    @BusinessLog("删除商品（慎用）")
    public ResultBean del(@RequestParam Integer id) {
        service.del(id);
        return ResultBean.success("删除成功");
    }

    @PostMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除")
    @BusinessLog(value = "批量删除")
    public ResultBean deleteBatch(@RequestParam Integer[] ids) {
        service.deleteBatch(Arrays.asList(ids));
        return ResultBean.success("删除成功");
    }

    @PostMapping(value = "/putaway")
    @ApiOperation(value = "上下架商品")
    @BusinessLog("上下架商品")
    public ResultBean putaway(@RequestParam Integer id,
                              @RequestParam String ifPutaway) {
        service.putaway(id, PutawayEnum.valueOf(ifPutaway));
        return ResultBean.success("删除成功");
    }

}