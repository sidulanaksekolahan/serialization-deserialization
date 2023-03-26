package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Paths;

@SpringBootApplication
public class SerializationDeserializationApplication {

	@Value("classpath:data/sample.json")
	Resource resource;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SerializationDeserializationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() throws IOException {
		// JSON TO JAVA POJO

		// Create object mapper
		ObjectMapper mapper = new ObjectMapper();

		// read JSON from file and map/convert to JAVA POJO
		Student student = mapper.readValue(resource.getFile(), Student.class);
		System.out.println(student);

		// JAVA POJO TO JSON

		// Indent the JSON output for "pretty printing"
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		// write JSON to output file
		mapper.writeValue(Paths.get("src/main/resources/data/output.json").toFile(), student);

		return null;
	}

}
