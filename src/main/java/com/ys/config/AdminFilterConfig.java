package com.ys.config;

import com.ys.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminFilterConfig {
    @Bean
    public AdminFilter adminFilter() {
        return new AdminFilter();
    }

    @Bean(name = "adminFilterConf")
    public FilterRegistrationBean adminFilterConfig() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(adminFilter());
        filterRegistrationBean.addUrlPatterns("/user/deleteUser");
        filterRegistrationBean.addUrlPatterns("/user/listUser");
        filterRegistrationBean.addUrlPatterns("/user/updateUser");
        filterRegistrationBean.addUrlPatterns("/user/getUser");
        filterRegistrationBean.addUrlPatterns("/equipment/*");
        filterRegistrationBean.addUrlPatterns("/component/*");
        filterRegistrationBean.addUrlPatterns("/change/*");
        filterRegistrationBean.setName("adminFilterConf");

        return filterRegistrationBean;
    }
}
