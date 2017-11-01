package vn.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import vn.todo.web.interceptor.ModelInterceptor;
import java.util.Locale;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ModelInterceptor modelInterceptor;

    @Autowired
    LocaleChangeInterceptor localeChangeInterceptor;

    @Bean("localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean("localeResolver")
    public CookieLocaleResolver cookieLocaleResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(new Locale("ru"));
        return clr;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(modelInterceptor).addPathPatterns("/**");
        registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**");
    }
}