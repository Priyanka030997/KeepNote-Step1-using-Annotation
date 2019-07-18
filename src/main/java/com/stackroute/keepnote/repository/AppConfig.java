package com.stackroute.keepnote.repository;

import com.stackroute.keepnote.model.Note;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "com.stackroute.keepnote")
public class AppConfig {
    @Bean
    public Note noteBean()
    {
        return new Note();
    }
    @Bean
    public NoteRepository noteRepositoryBean()
    {
        return new NoteRepository();
    }
    @Bean
    public ViewResolver viewResolver() {

    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
