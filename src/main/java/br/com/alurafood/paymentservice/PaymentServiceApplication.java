package br.com.alurafood.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = { "br.com.alurafood" })
@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
