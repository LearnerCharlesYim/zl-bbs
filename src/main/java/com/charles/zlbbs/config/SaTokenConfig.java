package com.charles.zlbbs.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.thymeleaf.dialect.SaTokenDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
public class SaTokenConfig {

    // Sa-Token 标签方言 (Thymeleaf版)
    @Bean
    public SaTokenDialect getSaTokenDialect() {
        return new SaTokenDialect();
    }

    // 为 Thymeleaf 注入全局变量，以便在页面中调用 Sa-Token 的方法
    @Autowired
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        viewResolver.addStaticVariable("stp", StpUtil.stpLogic);
        viewResolver.addStaticVariable("test", new MyTest());
    }

    static class MyTest{
        public static String m1(){
            return "hello world";
        }
    }


}
