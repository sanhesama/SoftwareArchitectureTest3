package com.wu.util;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;


//实现拦截器
public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 返回true放行，返回false不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 登陆成功只要，应该有用户的session
        HttpSession session = request.getSession();
        String number = (String) session.getAttribute("number");
        String password = (String) session.getAttribute("password");
        if(ObjectUtils.isEmpty(number) || ObjectUtils.isEmpty(password)){
            //没有登陆,不允许进入
            response.sendRedirect("handleLogin");
            return false;
        } else {
            // 登陆成功
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
