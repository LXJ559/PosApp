package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/*主要用来配置跨域请求，当前后端不在同一服务器时，如果不设置，只有get方法可以请求，其他的都是'OPTION'*/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserHandler());
        super.addArgumentResolvers(argumentResolvers);
    }
    //把当前用户的配置bean实例化
    @Bean
    public CurrentUserHandler currentUserHandler() {
        return new CurrentUserHandler();
    }

    /*自定义拦截器类执行是在Bean实例化前执行的，为此我们先将该拦截器类实例化，
    所以加上@Bean注解，这样才能获取到拦截器类中的redisTemplate*/
    @Bean
    public WebInterceptor getWebInterceptor(){
        return new WebInterceptor();
    }

    //将自定义拦截器注册，然后才能使用，除了login接口其他均可访问
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getWebInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/user/*","/**/admin","/**/*.js","/**/*.css","/**/*.ico","/**/*.html");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // classpath表示在resource目录下，/static/** 表示在URL路径中访问如
        // http://localhost:8080/static/ 即可访问到resource下的static目录
        registry.addResourceHandler("/**/*.js","/**/*.css","/**/*.ico").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


    //全局设置来自前端的跨域请求可以访问哪些接口
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedMethods("*")
                        .maxAge(3600);
            }
        };
    }

    //设置前端服务器可以访问此后端，不设置就是会有跨域的阻碍而不能请求
    @Bean
    public FilterRegistrationBean corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOrigin("*");
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }

    //向容器注册自定义视图解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new I18nConfig();
    }
}
