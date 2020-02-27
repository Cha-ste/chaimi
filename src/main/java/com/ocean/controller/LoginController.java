package com.ocean.controller;

import com.ocean.entity.User;
import com.ocean.redis.RedisService;
import com.ocean.redis.UserPrefix;
import com.ocean.service.UserService;
import com.ocean.utils.Constants;
import com.ocean.utils.JedisUtils;
import com.ocean.utils.TokenUtils;
import com.ocean.vo.CodeMsg;
import com.ocean.vo.LoginVo;
import com.ocean.vo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@Api(value="登录接口", tags = "登录接口")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    /**
     * 用户登录
     */
    @GetMapping("login")
    @ApiOperation("用户登录")
    public ResultBean<String> login (@Valid LoginVo loginVo) {

        User user = userService.getUserByMobile(loginVo.getMobile());
        if (user == null) {
            return ResultBean.error(CodeMsg.NO_USER);
        }
        if (!loginVo.getPassword().equals(user.getPassword())) {
            return ResultBean.error(CodeMsg.PASSWORD_ERROR);
        }
        //登录成功就生成token
        Map<String, Object> payload = new HashMap<>();
        Date date = new Date();
        payload.put("uid", user.getId());
        payload.put("ct", date.getTime());
        payload.put("ext", date.getTime() + 1000*60*2); //设置两个小时过期
        String token = TokenUtils.createToken(payload);

        redisService.set(UserPrefix.getByToken, token, user.getId(), Constants.TOKEN_EXPIRED_SECOND);
//        redisService.set(UserPrefix.getById, user.getId(), user);
        User user1 = redisService.get(UserPrefix.getById, user.getId(), User.class);

        return ResultBean.success(token);
    }

    @GetMapping("logout")
    @ApiOperation("退出登录")
    public ResultBean<String> logout (HttpServletRequest request, @RequestParam String mobile) {

        User user = userService.getUserByMobile(mobile);
        if (user == null) {
            return ResultBean.error(CodeMsg.NO_USER);
        }

        redisService.delete(UserPrefix.getByToken, request.getHeader("token"));

        return ResultBean.success("退出成功");
    }
}
