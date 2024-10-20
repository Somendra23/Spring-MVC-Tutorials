package com.dailycodebuffer.transaction.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dailycodebuffer.transaction.filter.TransactionFilter;
import com.dailycodebuffer.transaction.interceptor.EmployeeInterceptor;

@Configuration
public class Webconfig implements WebMvcConfigurer{

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new EmployeeInterceptor())
                .addPathPatterns("/employee/**"); // Specify the URL patterns to apply the interceptor
    }


@Bean
public FilterRegistrationBean<TransactionFilter> registerFilters(){
	
	FilterRegistrationBean<TransactionFilter> tf = new FilterRegistrationBean<>();
	tf.setFilter(new TransactionFilter());
	tf.addUrlPatterns("/employee/*");
	tf.setOrder(1);
	
	return tf;
	
}
}
