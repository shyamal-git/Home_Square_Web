package com.homeSquare.config;

import com.homeSquare.filter.PropertyApprovalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<PropertyApprovalFilter> propertyApprovalFilter() {
        FilterRegistrationBean<PropertyApprovalFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new PropertyApprovalFilter());
        registrationBean.addUrlPatterns("/api/properties/*/approve"); // Apply the filter only to approve endpoints
        return registrationBean;
    }
}
