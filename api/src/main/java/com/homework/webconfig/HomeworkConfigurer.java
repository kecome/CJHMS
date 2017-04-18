package com.homework.webconfig;

import com.homework.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置
 *
 * @author
 * @create 2017-03-31 上午 11:21
 **/
@EnableWebMvc
@Configuration
public class HomeworkConfigurer extends WebMvcConfigurerAdapter {

    @Value("${cbp_host}")
    private String host;

    @Autowired
    private Environment env;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(env)).addPathPatterns("/**").excludePathPatterns("/**.html");
        super.addInterceptors(registry);
    }
}
