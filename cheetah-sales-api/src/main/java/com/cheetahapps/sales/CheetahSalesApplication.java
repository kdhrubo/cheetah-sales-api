package com.cheetahapps.sales;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CheetahSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheetahSalesApplication.class, args);
	}

}
