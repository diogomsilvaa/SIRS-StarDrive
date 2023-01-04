package pt.sirs.app.StarDrive;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import pt.sirs.app.StarDrive.auth.AuthService;
import pt.sirs.app.StarDrive.user.UserService;
import pt.sirs.app.StarDrive.user.domain.User;


@SpringBootApplication
public class StarDriveApplication extends SpringBootServletInitializer implements InitializingBean{

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(StarDriveApplication.class, args);
	}

	@Override
    public void afterPropertiesSet() {

		AuthService authService = new AuthService("[B@49ad6061");
		try {
			String message = authService.decrypt();
			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

        // Run on startup
		userService.createUser("Diogo", User.Role.ENGINEER, 2000.0);
		userService.createUser("Miguel", User.Role.EMPLOYEE, 100.0);
		userService.createUser("Carlos", User.Role.EMPLOYEE, 1500.0);
		System.out.println("StarDrive Application Started");
    }
}
