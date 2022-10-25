package com.yash.blog;
/**
 * @author vishwendra.singh
 */

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yash.blog.config.AppConstants;
import com.yash.blog.dao.RoleRepository;
import com.yash.blog.model.Role;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository rolerepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelmapper()
		{
			return new ModelMapper();
		}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(this.passwordEncoder.encode("password"));
		
		try {
			Role role= new Role();
			role.setId(AppConstants.ROLE_NORMAL);
			role.setName("ROLE_NORMAL");
			
			Role role1= new Role();
			role1.setId(AppConstants.ROLE_ADMIN);
			role1.setName("ROLE_ADMIN");
			
			List<Role> roles=List.of(role,role1);
			List<Role> rolelist= this.rolerepository.saveAll(roles);
			
//			rolelist.forEach(r->{System.out.println(r.getName());});
		}
		catch(Exception e)
		{
			
		}
		
	}

}
