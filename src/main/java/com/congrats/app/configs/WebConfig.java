package com.congrats.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        // Permitir CORS para el origen específico en la ruta de correo electrónico
        registry.addMapping("/api/mail/**")
                .allowedOrigins("http://localhost:4200", "https://peky17.github.io")
                .allowedMethods("POST")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Access-Control-Allow-Origin");

        // Permitir CORS en todas las rutas de la API
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200", "https://peky17.github.io")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Access-Control-Allow-Origin");
    }
}
