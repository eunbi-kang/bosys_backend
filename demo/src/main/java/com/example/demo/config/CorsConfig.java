package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // ✅ 특정 출처 허용 (React에서 요청을 보낼 수 있도록 설정)
        config.setAllowedOrigins(List.of("http://localhost:3000"));

        // ✅ 모든 HTTP 메서드 허용 (GET, POST, PUT, DELETE 등)
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // ✅ 모든 헤더 허용
        config.setAllowedHeaders(List.of("*"));

        // ✅ 인증 관련 허용 (Authorization 헤더 허용)
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
