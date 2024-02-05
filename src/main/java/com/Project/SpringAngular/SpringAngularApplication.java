package com.Project.SpringAngular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularApplication.class, args);
	}

}
//docker run -e DB_HOST=my-db-host -e DB_PORT=3306 -e DB_NAME=my-database -e DB_USERNAME=my-username -e DB_PASSWORD=my-password -p 8083:808
//3 image-upload:v2.0