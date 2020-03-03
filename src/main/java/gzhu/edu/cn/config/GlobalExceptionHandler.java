package gzhu.edu.cn.config;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gzhu.edu.cn.base.log.entity.LogInfo;
import gzhu.edu.cn.base.log.service.ILogInfoService;
import gzhu.edu.cn.base.util.AddressUtils;
import gzhu.edu.cn.base.util.UserUtils;

@ControllerAdvice // 作为一个控制层的切面处理
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error"; // 定义错误显示页，error.html

    @Autowired
    public ILogInfoService logInfoService;
    /**
     * 处理服务器内部异常
     * @param request
     * @param e
     * @param model
     * @return
     */
    
    @ExceptionHandler(Exception.class) // 所有的异常都是Exception子类
    public String defaultErrorHandler(HttpServletRequest request, Exception e,Model model) { // 出现异常之后会跳转到此方法
        model.addAttribute("exception", e.getLocalizedMessage()); // 将异常对象传递过去
        model.addAttribute("url", request.getRequestURL()); // 获得请求的路径
        
        LogInfo logInfo = new LogInfo();
        
        logInfo.setOperation("异常页面");
        
        logInfo.setMessage(e.getMessage());
        
     // 设置IP地址
        logInfo.setIp(AddressUtils.getIpAddr(request));
        
        //获取请求地址
        logInfo.setUrl(AddressUtils.getRequestURL(request));
        
        // 获取用户信息
        String username = UserUtils.getCurrentUserName();
        if(username!=null){
        	logInfo.setUsername(username);
        }
        
        logInfo.setCreateTime(new Date());
        // 保存系统日志
        logInfoService.save(logInfo);
        
        return "error/error";
    }
    
}
