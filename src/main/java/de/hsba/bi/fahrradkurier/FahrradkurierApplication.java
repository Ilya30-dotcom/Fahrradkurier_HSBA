package de.hsba.bi.fahrradkurier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class FahrradkurierApplication {

	public static void main(String[] args) {
		SpringApplication.run(FahrradkurierApplication.class, args);
	}
	@Bean
	public Clock clock(){
		return Clock.systemDefaultZone();
	}
}
