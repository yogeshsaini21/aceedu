package com.masai.team6;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.masai.team6.Entities.Role;
import com.masai.team6.Repository.RoleRepo;
import com.masai.team6.Services.EmailService;
import com.masai.team6.config.AppConstants;

@SpringBootApplication
@ComponentScan(basePackages = "com.masai.team6")
@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.masai.team6.Repository")
//@EnableSwagger2
public class Team6Application implements CommandLineRunner{

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(Team6Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public MultipartResolver multipartResolver() {
	    return new CommonsMultipartResolver();
	}
	 
	
	 public void run(String... args) throws Exception {
	

		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			ArrayList<Role> roles = new ArrayList<Role>();
			roles.add(role);
			roles.add(role1);

			java.util.List<Role> result = this.roleRepo.saveAll(roles);
				
			System.out.println("Application is started");
			for(int i=0;i<100;i++)
			emailService.sendEmail("2021pietcssaloni152@poornima.org", "sunil weekly Repot Card", "sunil weekly progress 82% Attendence in Test out of ");
//			2021pietcssaloni152@poornima.org

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
