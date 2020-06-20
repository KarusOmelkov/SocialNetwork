package com.uspu.Cupcake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CupcakeApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CupcakeApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/tinymce/**").addResourceLocations("classpath:/tinymce/");
		registry.addResourceHandler("/image/**").addResourceLocations("classpath:/image/");
	}

}
