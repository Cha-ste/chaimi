package com.ocean.aop;

import com.ocean.entity.Logs;
import com.ocean.entity.User;
import com.ocean.service.LogsService;
import com.ocean.service.UserService;
import com.ocean.utils.HttpUtil;
import com.ocean.utils.TokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class BusinessAop {
    @Autowired
    UserService userService;
    @Autowired
    LogsService logsService;

    @Pointcut(value = "@annotation(com.ocean.aop.BusinessLog)")
    public void cutService() {}

    @Around("cutService()")
    public Object logStorage(ProceedingJoinPoint joinPoint) throws Throwable {
        //先执行业务
        Object result = joinPoint.proceed();

        handleLog(joinPoint);

        return result;
    }

    /**
     * 日志保存
     */
    private void handleLog(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature)signature;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        // 获取注解参数
        BusinessLog annotation = currentMethod.getAnnotation(BusinessLog.class);
        String bussinessName = annotation.value();

        // 获取操作人
        Integer userId = TokenUtils.getUserId(HttpUtil.getToken());
        User user = userService.getUser(userId);

        // 保存日志
        Logs logs = new Logs();
        logs.setBusiness(bussinessName);
        logs.setOperateTime(new Date());
        logs.setOperator(user.getUsername());
        logsService.save(logs);
    }
}
