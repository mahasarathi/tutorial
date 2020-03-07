package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.example.demo.entity.AppUser;
import com.example.demo.enums.AppUserStatus;
import com.example.demo.repository.AppUserHistoryRepository;
import com.example.demo.repository.AppUserRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = "SpringBoot_BOQN_Demo Rest API", version = "0.0.1-SNAPSHOT", license = @License(name = "Apache 2.0 License", url = "http://www.apache.org/licenses/LICENSE-2.0.html"), contact = @Contact(email = "mahasarathi22_11@yahoo.com", name = "MahaSarathi"), description = "SpringBoot_BOQN_Demo spring boot application to show basic REST CURD operations."))
@SpringBootApplication
public class SpringBootBOQNDemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootBOQNDemoApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootBOQNDemoApplication.class,
				args);

		Environment environment = applicationContext.getEnvironment();
		LOGGER.info(
				"\r\n------------------------------------------------------------------------------\r\n\t"
						+ "Appliation '{}' is running! Access URLs:\r\n\t" 
						+ "Local:\thttp://localhost:{}{}\r\n\t"
						+ "External:\thttp://{}:{}{}\r\n"
						+ "-----------------------------------------------------------------------------------",
				environment.getProperty("spring.application.name"), environment.getProperty("server.port"),
				environment.getProperty("server.servlet.contextPath"), InetAddress.getLocalHost().getHostAddress(),
				environment.getProperty("server.port"), environment.getProperty("server.servlet.contextPath"));
	}

	@Bean
	CommandLineRunner init(AppUserRepository appUserRepository, AppUserHistoryRepository appUserHistoryRepository) {
		return args -> {
			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				AppUser appUser = AppUser.of(name, name.toLowerCase() + "@domain.com");
				appUser = appUserRepository.save(appUser);

				appUser.setEmail("new_" + appUser.getEmail());
				appUser = appUserRepository.save(appUser);

				appUser.setStatus(AppUserStatus.DELETED);
				appUserRepository.save(appUser);
			});

			Stream.of("temp1", "temp2").forEach(name -> {
				AppUser appUser = AppUser.of(name, name.toLowerCase() + "@domain.com");
				appUser = appUserRepository.save(appUser);
			});

			appUserRepository.findAll().forEach(appUser -> {
				LOGGER.debug("APPUSER --> {}", appUser);
			});

			appUserHistoryRepository.findAll().forEach(appUserHistory -> {
				LOGGER.debug("APPUSER_HISTORY --> {}", appUserHistory);
			});
		};
	}

}
