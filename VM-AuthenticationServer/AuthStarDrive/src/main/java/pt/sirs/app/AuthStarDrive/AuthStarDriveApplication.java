package pt.sirs.app.AuthStarDrive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;

import pt.sirs.app.AuthStarDrive.Authentication.AuthenticationService;

@SpringBootApplication
public class AuthStarDriveApplication extends SpringBootServletInitializer implements InitializingBean{

	@Autowired
	private AuthenticationService authService;

	public static void main(String[] args) {
		SpringApplication.run(AuthStarDriveApplication.class, args);
	}

	@Override
    public void afterPropertiesSet() {
        // Run on startup
		File file = null;
		try {
			file = new ClassPathResource("data/CreateUsers.txt").getFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = null;
		String st = null;
		try {
			br = new BufferedReader(new FileReader(file));
			st = br.readLine();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		while (st != null) {
			String[] arrOfStr = st.split(":", 2);
			try {
				authService.addUser(arrOfStr[0], arrOfStr[1]);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			try {
				st = br.readLine();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		try {
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// tests
		try {
			String token = authService.doLogin("U0", "password1");
			System.out.println(token.toString());
			System.out.println("Login success");
		} catch (Exception e) {
			System.out.println("Login failed");
		}
    }
}
