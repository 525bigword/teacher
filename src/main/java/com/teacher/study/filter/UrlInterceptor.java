package com.teacher.study.filter;

import com.teacher.study.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class UrlInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
          HttpServletRequest request, HttpServletResponse response, Object handler) {
    String authorization = request.getHeader("Authorization");
    if(request.getRequestURI().equals(request.getContextPath()+"/classify/get")){
      return true;
    }
    System.out.println(request.getRequestURI());
    System.out.println(request.getContextPath()+"/classify/get");
    if(authorization==null){
      log.info("权限不足,无请求头");
      throw new RuntimeException("权限不足无请求头");
    }else if(!authorization.startsWith("teacher")){
      log.info("权限不足,请求头异常");
      throw new RuntimeException("权限不足,请求头异常");
    }
    String token=authorization.substring(7);
    Claims claims = JwtUtil.parseJWT(token);
    if(claims==null||"".equals(claims)){
      log.info("权限不足,无请求头信息");
      throw new RuntimeException("无请求头信息");
    }
    return true;
  }
}
