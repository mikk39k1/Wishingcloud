package com.example.ourwishlist.config;

import com.example.ourwishlist.interceptor.NoCacheInterceptor;
import com.example.ourwishlist.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/homepage")
                .addPathPatterns("/friendWishList/**")
                .addPathPatterns("/editprofile")
                .addPathPatterns("/profile")
                .addPathPatterns("/friendrequest")
                .addPathPatterns("/addFriend") // Protected paths pattern
                .excludePathPatterns("/authorization/login", "/authorization/register"); // Exclude login and register paths

        registry.addInterceptor(new NoCacheInterceptor())
                .addPathPatterns("/homepage", "/friendWishList/**", "/editprofile", "/profile", "/addFriend", "/friendrequest");
    }
}
