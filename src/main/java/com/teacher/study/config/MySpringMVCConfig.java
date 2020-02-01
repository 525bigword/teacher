package com.teacher.study.config;

import com.teacher.study.filter.UrlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

//@Configuration
public class MySpringMVCConfig extends WebMvcConfigurationSupport {
  @Autowired
  private UrlInterceptor interceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(interceptor).addPathPatterns("/**")
    .excludePathPatterns("/user/login","/index/**","/courseware/file")
    ;
    //WebMvcConfigurer.super.addInterceptors(registry);
  }
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new BufferedImageHttpMessageConverter());
    }

}
