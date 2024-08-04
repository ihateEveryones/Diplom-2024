package com.cb.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/image/**") // Ваш кастомный путь
                .addResourceLocations("file:///C:/Users/vladz/Desktop/image/"); // Путь к вашим изображениям
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
}
