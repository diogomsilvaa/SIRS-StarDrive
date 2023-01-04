package pt.sirs.app.AuthStarDrive;

import java.io.BufferedReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		File file = new File("CreateUsers.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = br.readLine();
		while (st != null) {
			String[] arrOfStr = str.split(":", 2);
			try {
				authService.addUser(arrOfStr[0], arrOfStr[1]);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
        br.close();
    }
}
