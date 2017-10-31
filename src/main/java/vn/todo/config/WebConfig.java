package vn.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import vn.todo.web.interceptor.ModelInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ModelInterceptor modelInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(modelInterceptor).addPathPatterns("/**");
    }
}