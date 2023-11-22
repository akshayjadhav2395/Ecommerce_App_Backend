package com.myEcom;

import com.myEcom.entity.Role;
import com.myEcom.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EcommerceAppBackendApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAppBackendApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			Role role=new Role();
			role.setId(5432);
			role.setName("ROLE_ADMIN");

			Role role1=new Role();
			role1.setId(6543);
			role1.setName("ROLE_NORMAL");

			Role role2=new Role();
			role2.setId(7654);
			role2.setName("ROLE_STAFF");

//			List<Role> roleList=new ArrayList<>();
//			roleList.add(role);
//			roleList.add(role1);
//			roleList.add(role2);

			roleRepository.saveAll(List.of(role, role1, role2));
		}
		catch (Exception exception)
		{
			System.out.println("User Already there...!");
		}


	}
}
