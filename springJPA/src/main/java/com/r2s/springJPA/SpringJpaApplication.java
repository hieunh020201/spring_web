package com.r2s.springJPA;

import com.r2s.springJPA.entity.API;
import com.r2s.springJPA.entity.Role;
import com.r2s.springJPA.entity.User;
import com.r2s.springJPA.repository.APIRepository;
import com.r2s.springJPA.repository.RoleRepository;
import com.r2s.springJPA.repository.UserRepository;
import com.r2s.springJPA.util.Constants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.*;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, APIRepository apiRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Role roleAdmin = new Role(1, "ADMIN", new HashSet<>(), null);
				Role roleUser = new Role(2, "USER", new HashSet<>(), null);

				roleRepository.save(roleAdmin);
				roleRepository.save(roleUser);

				Set<Role> roleSetAdmin = new HashSet<>();
				roleSetAdmin.add(roleAdmin);

				Set<Role> roleSetAdminUser = new HashSet<>();
				roleSetAdminUser.add(roleAdmin);
				roleSetAdminUser.add(roleUser);

				Set<Role> roleSetUser = new HashSet<>();
				roleSetUser.add(roleUser);

				User adminUser = new User(1, "ADMINUSER", "adminuser", passwordEncoder.encode("123456"),
						"adminuser@gmail.com", "0123456789", Date.valueOf("2019-08-12"), "male", roleSetAdminUser, false, new ArrayList<>(), new ArrayList<>());
				userRepository.save(adminUser);

				User user = new User(2, "USER", "user", passwordEncoder.encode("123456"),
						"user@gmail.com", "0123456789", Date.valueOf("2019-08-12"), "male", roleSetUser, false, new ArrayList<>(), new ArrayList<>());
				userRepository.save(user);

				User admin = new User(3, "ADMIN", "admin", passwordEncoder.encode("123456"),
						"admin@gmail.com", "0123456789", Date.valueOf("2019-08-12"), "male", roleSetAdmin, false, new ArrayList<>(), new ArrayList<>());
				userRepository.save(admin);

				API getAllAPIs = new API(1, "(/apis)$", roleSetAdmin, "GET", false);
				apiRepository.save(getAllAPIs);
				API insertAPI = new API(2, "(/api)$", roleSetAdmin, "POST", false);
				apiRepository.save(insertAPI);
				API getAPIById = new API(3, "(/api)([0-9]{1,10})$", roleSetAdmin, "GET", false);
				apiRepository.save(getAPIById);
				API updateAPI = new API(4, "(/api)([0-9]{1,10})$", roleSetAdmin, "PUT", false);
				apiRepository.save(updateAPI);
				API deleteAPI = new API(5, "(/api)([0-9]{1,10})$", roleSetAdmin, "DELETE", false);
				apiRepository.save(deleteAPI);


				API getALlUsers = new API(6, "(/users)$", roleSetAdmin, "GET", false);
				apiRepository.save(getALlUsers);
				API getUserByUserId = new API(7, "(/user/)([0-9]{1,10})$", roleSetAdmin, "GET", false);
				apiRepository.save(getUserByUserId);
				API insertUser = new API(8, "(/user)$", roleSetAdmin, "POST", false);
				apiRepository.save(insertUser);
				API updateUser = new API(9, "(/user/)([0-9]{1,10})$", roleSetAdmin, "PUT", false);
				apiRepository.save(updateUser);
				API deleteUser = new API(10, "(/user/)([0-9]{1,10})$", roleSetAdmin, "DELETE", false);
				apiRepository.save(deleteUser);


				API getALlAddress = new API(11, "(/addresses)$", roleSetAdmin, "GET", false);
				apiRepository.save(getALlAddress);
				API getAddressByAddressId = new API(12, "(/address/)([0-9]{1,10})$", roleSetAdmin, "GET", false);
				apiRepository.save(getAddressByAddressId);
				API insertAddress = new API(13, "(/address)$", roleSetAdmin, "POST", false);
				apiRepository.save(insertAddress);
				API updateAddress = new API(14, "(/address/)([0-9]{1,10})$", roleSetAdmin, "PUT", false);
				apiRepository.save(updateAddress);
				API deleteAddress = new API(15, "(/address/)([0-9]{1,10})$", roleSetAdmin, "DELETE", false);
				apiRepository.save(deleteAddress);

				API insertAddressByUser = new API(16, "(/user/)([0-9]{1,10})(/address)$", roleSetAdminUser, "POST", false);
				apiRepository.save(insertAddressByUser);
				API getListAddressesByUser = new API(17, "(/user/)([0-9]{1,10})(/addresses)$", roleSetAdminUser, "GET", false);
				apiRepository.save(getListAddressesByUser);
				API getAddressByUser = new API(18, "(/user/)([0-9]{1,10})(/address/)([0-9]{1,10})$", roleSetAdminUser, "GET", false);
				apiRepository.save(getAddressByUser);
				API updateAddressByUser = new API(19, "(/user/)([0-9]{1,10})(/address/)([0-9]{1,10})$", roleSetAdminUser, "PUT", false);
				apiRepository.save(updateAddressByUser);
				API deleteAddressByUser = new API(20, "(/user/)([0-9]{1,10})(/address/)([0-9]{1,10})$", roleSetAdminUser, "DELETE", false);
				apiRepository.save(deleteAddressByUser);

				API insertCategory = new API(21, "(/category)$", roleSetAdmin, "POST", false);
				apiRepository.save(insertCategory);
				API updateCategory = new API(22, "(/category/)([0-9]{1,10})$", roleSetAdmin, "PUT", false);
				apiRepository.save(updateCategory);
				API deleteCategory = new API(23, "(/category/)([0-9]{1,10})$", roleSetAdmin, "DELETE", false);
				apiRepository.save(deleteCategory);

				API insertProduct = new API(24, "(/category/)([0-9]{1,10})(/product)$", roleSetAdmin, "POST", false);
				apiRepository.save(insertProduct);
				API updateProduct = new API(25, "(/category/)([0-9]{1,10})(/product/)([0-9]{1,10})$", roleSetAdmin, "PUT", false);
				apiRepository.save(updateProduct);
				API deleteProduct = new API(26, "(/category/)([0-9]{1,10})(/product/)([0-9]{1,10})$", roleSetAdmin, "DELETE", false);
				apiRepository.save(deleteProduct);

				API insertCartByUser = new API(27, "(/user/)([0-9]{1,10})(/cart)$", roleSetUser, "POST", false);
				apiRepository.save(insertCartByUser);
				API getListCartsByUser = new API(28, "(/user/)([0-9]{1,10})(/carts)$", roleSetUser, "GET", false);
				apiRepository.save(getListCartsByUser);
				API getCartByUser = new API(29, "(/user/)([0-9]{1,10})(/cart/)([0-9]{1,10})$", roleSetUser, "GET", false);
				apiRepository.save(getCartByUser);
				API updateCartByUser = new API(30, "(/user/)([0-9]{1,10})(/cart/)([0-9]{1,10})$", roleSetUser, "PUT", false);
				apiRepository.save(updateCartByUser);
				API deleteCartByUser = new API(31, "(/user/)([0-9]{1,10})(/cart/)([0-9]{1,10})$", roleSetUser, "DELETE", false);
				apiRepository.save(deleteCartByUser);

				API insertCartDetailByUser = new API(32, "(/user/)([0-9]{1,10})(/cart/)([0-9]{1,10})(/detail)$", roleSetUser, "POST", false);
				apiRepository.save(insertCartDetailByUser);
				API getDetailsCartByUser = new API(33, "(/user/)([0-9]{1,10})(/cart/)([0-9]{1,10})(/details)$", roleSetUser, "GET", false);
				apiRepository.save(getDetailsCartByUser);
				API getDetailCartByUser = new API(34, "(/user/)([0-9]{1,10})(/cart/)([0-9]{1,10})(/detail/)([0-9]{1,10})$", roleSetUser, "GET", false);
				apiRepository.save(getDetailCartByUser);
				API updateDetailCartByUser = new API(35, "(/user/)([0-9]{1,10})(/cart/)([0-9]{1,10})(/detail/)([0-9]{1,10})$", roleSetUser, "PUT", false);
				apiRepository.save(updateDetailCartByUser);
				API deleteDetailCartByUser = new API(36, "(/user/)([0-9]{1,10})(/cart/)([0-9]{1,10})(/detail/)([0-9]{1,10})$", roleSetUser, "DELETE", false);
				apiRepository.save(deleteDetailCartByUser);

//				API getALlUsers = new API(15, "(/users)$", roleSetAdmin, "DELETE", false);
//				apiRepository.save(getALlUsers);
//				API getALlUsers = new API(16, "(/users)$", roleSetAdmin, "DELETE", false);
//				apiRepository.save(getALlUsers);






//				List<API> list = new ArrayList<>();
//				list.add(new API(Constants.BASE_URL + "(/users)$", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "GET"));
//				list.add(new API(Constants.BASE_URL + "(/user)$", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "POST"));
//				list.add(new API(Constants.BASE_URL + "(/user)$", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "PUT"));
//				list.add(new API(Constants.BASE_URL + "(/user/)([0-9]{1,4})$", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "DELETE"));
//				list.add(new API(Constants.BASE_URL + "(/user/)([0-9]{1,4})$", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "GET"));
//
//				Constants.setAPI(list);
			}
		};
	}

}
