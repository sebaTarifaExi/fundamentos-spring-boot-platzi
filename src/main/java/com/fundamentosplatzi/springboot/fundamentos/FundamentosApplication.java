package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanPropieties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanPropieties myBeanPropieties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	private UserService userService;

	public FundamentosApplication (@Qualifier("componentImplementTwo") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanPropieties myBeanPropieties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanPropieties = myBeanPropieties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1,test2,test3,test4);
		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una excepcion dentro del metodo transccional: "+e);
		}
			userService.getAllUsers().stream()
					.forEach(user -> LOGGER.info("Este es el usuario por metodo transaccional: "+user));
		}

		private void getInformationJpqlFromUser(){
            /* LOGGER.info("Usuario con el metodo findByUserEmail: " + userRepository.findByUserEmail("julie@domain.com")
					.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

			userRepository.findAndSort("user", Sort.by("id").descending())
					.stream()
					.forEach(user -> LOGGER.info("Usuario con el metodo find and Sort: "+user));

			userRepository.findByName("John")
					.stream()
					.forEach(user -> LOGGER.info("Usuario encontrado por query method: "+user));

			LOGGER.info("Usuario encontrado con query method name and email: " + userRepository.findByEmailAndName("daniela@domain.com","Daniela")
					.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

			userRepository.findByNameLike("%user%")
					.stream()
					.forEach(user -> LOGGER.info("Usuarios encontrados por query method findByNameLike: " + user));

			userRepository.findByNameOrEmail(null,"user10@domain.com")
					.stream()
					.forEach(user -> LOGGER.info("Usuarios encontrados por query method findByNameOrEmail: " + user));*/

			userRepository
					.findByBirthDateBetween(LocalDate.of(2021,3,1), LocalDate.of(2021,4,2))
					.stream()
					.forEach(user -> LOGGER.info("Usuarios encontrados por findByBirthDateBetween: " + user));

			userRepository.findByNameContainingOrderByIdDesc("user")
					.stream()
					.forEach(user -> LOGGER.info("Usuarios encontrados por findByNameLikeOrderByIdDesc: " + user));

		//	LOGGER.info("El usuario encontrado a partir del named parad es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021,7,21),"daniela@domain.com")
			//		.orElseThrow(()-> new RuntimeException("No se encontro el usuario a partir del named param")));
		}


		private void saveUsersInDataBase(){
			User user1 = new User("John", "jonh@domain.com", LocalDate.of(2021,3,20));
			User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021,5,21));
			User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021,7,21));
			User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021,7,7));
			User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021,11,11));
			User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021,2,25));
			User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021,3,11));
			User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021,4,22));
			User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021,5,22));
			User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021,8,3));
			User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021,1,12));
			User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021,2,2));

			List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12);
			list.stream().forEach(userRepository::save);
		}

		private void ejemplosAnteriores(){
			componentDependency.saludar();
			myBean.print();
			myBeanWithDependency.printWithDependency();
			System.out.println(myBeanPropieties.function());
			System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
			try {
				//error
				int value = 10 / 0;
				LOGGER.debug("Mi valor: "+value);
			}catch (Exception e){
				LOGGER.error("Esto es un error al dividir por 0 "+e.getMessage());
			}
		}
}


