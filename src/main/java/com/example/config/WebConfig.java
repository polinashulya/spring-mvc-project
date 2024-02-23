package com.example.config;

import com.example.controller.binder.StringToEmployeePositionConverter;
import com.example.controller.binder.StringToProcedureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/WEB-INF/jsp/**").addResourceLocations("/jsp/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }


    @Autowired
    private StringToEmployeePositionConverter stringToEmployeePositionConverter;

    @Autowired
    private StringToProcedureConverter stringToProcedureConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToEmployeePositionConverter);
        registry.addConverter(stringToProcedureConverter);
    }
}
