package com.example.mangaapi;

import com.example.mangaapi.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class MangaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangaApiApplication.class, args);
	}

}
