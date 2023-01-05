package pt.sirs.app.StarDrive;

import java.time.LocalDateTime;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import pt.sirs.app.StarDrive.user.UserService;
import pt.sirs.app.StarDrive.user.domain.User;

import pt.sirs.app.StarDrive.shiftManager.ShiftManagerService;


@SpringBootApplication
public class StarDriveApplication extends SpringBootServletInitializer implements InitializingBean{

	@Autowired
	private UserService userService;

	@Autowired
	private ShiftManagerService shiftManagerService;

	public static void main(String[] args) {
		SpringApplication.run(StarDriveApplication.class, args);
	}

	@Override
    public void afterPropertiesSet() {

        // Run on startup
		User u1 = userService.createUser("Diogo", User.Role.ENGINEER, 2000.0);
		
		userService.addAbsentDay(u1.getId(), "2023/01/01");
		userService.addAbsentDay(u1.getId(), "2022/01/01");
		userService.addAbsentDay(u1.getId(), "2022/11/21");

		User u2 = userService.createUser("Miguel", User.Role.EMPLOYEE, 100.0);

		LocalDateTime startDateTime = LocalDateTime.now();
		LocalDateTime finishDateTime = LocalDateTime.now().plusHours(2);


		shiftManagerService.addEmployee(shiftManagerService.createShift(startDateTime,finishDateTime).getId(), u2.getId());

		
		userService.createUser("Carlos", User.Role.EMPLOYEE, 1500.0);
		System.out.println("StarDrive Application Started");
    }
}
