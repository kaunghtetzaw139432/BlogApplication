package com.blogApplication.Blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Blog Application Project",
		description = "Backend Rest APIs for Blog Application",
		version = "v1.0",
		contact = @Contact(
			name = "Kaung Htet Zaw",
				email = "zawk29006@gmail.com",
			url = "https://github.com/kaunghtetzaw139432/Blog-Application"
		),
		license = @License(
				name = "Blog Application Project",
				url = "https://github.com/kaunghtetzaw139432/Blog-Application"
		)
		
	),
	externalDocs = @ExternalDocumentation(
			description = "Backend Rest APIs for Blog Application",
				url = "https://github.com/kaunghtetzaw139432/Blog-Application"
	)
)
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);

	}


}
