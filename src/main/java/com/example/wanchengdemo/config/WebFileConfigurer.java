package com.example.wanchengdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebFileConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 虚拟路径映射
         */
//        registry.addResourceHandler("/video/**").addResourceLocations("classpath:/video/");
        registry.addResourceHandler("/*.html").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }
}