package gzhu.edu.cn.base.log.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import gzhu.edu.cn.base.log.annotation.Log;
import gzhu.edu.cn.base.log.entity.LogInfo;
import gzhu.edu.cn.base.log.service.ILogInfoService;
import gzhu.edu.cn.base.util.AddressUtils;
import gzhu.edu.cn.base.util.UserUtils;

@Aspect
@Component
public class LogAspect {
	
    @Autowired
    private ILogInfoService logInfoService;

	@Autowired
	private HttpServletRequest request;
	
    
    @Pointcut("@annotation(gzhu.edu.cn.base.log.annotation.Log)")
    public void pointcut() { }
    
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        String username  = null;
        long beginTime = System.currentTimeMillis();
        try {
        	username = UserUtils.getCurrentUserName();
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(username,point, time);
        return result;
    }
    
    private void saveLog(String username,ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogInfo logInfo = new LogInfo();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
        	logInfo.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logInfo.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            logInfo.setParams(params);
        }
        // 设置IP地址
        logInfo.setIp(AddressUtils.getIpAddr(request));
        
        //获取请求地址
        logInfo.setUrl(AddressUtils.getRequestURL(request));
        
        // 获取用户信息
        if(username!=null){
        	logInfo.setUsername(username);
        }
        
        logInfo.setTime((int) time);
        logInfo.setCreateTime(new Date());
        // 保存系统日志
        logInfoService.save(logInfo);
    }
}