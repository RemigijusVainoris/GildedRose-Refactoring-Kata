package com.vainoris.gildedroseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GildedRoseServiceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(GildedRoseServiceApplication.class, args);
	}

}
