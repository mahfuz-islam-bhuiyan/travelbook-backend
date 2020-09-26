package org.travelbook.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.travelbook.backend.dao.persistence")
@SpringBootApplication
public class TravelBookBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelBookBackendApplication.class, args);
	}

}
