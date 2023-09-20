package com.green.booktodolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BookTodoListApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookTodoListApplication.class, args);
    }
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedHeaders("*") // 어떤 헤더들을 허용할 것인지
						.allowedMethods("*") // 어떤 메서드를 허용할 것인지 (GET, POST…)
						.allowedOrigins("https://web-bookplanner-2rrqq2blmq2qgkt.sel5.cloudtype.app");
			}
		};
	}
}
