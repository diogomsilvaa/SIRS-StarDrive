package pt.sirs.app.StarDrive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import pt.sirs.app.StarDrive.production.domain.AssemblyLine;
import pt.sirs.app.StarDrive.production.repository.AssemblersRepository;
import pt.sirs.app.StarDrive.production.repository.LinesRepository;
import pt.sirs.app.StarDrive.production.ProductionService;

@SpringBootApplication
@EnableMongoRepositories
public class StarDriveApplication {

    @Autowired
    LinesRepository linesRepository;
    
    @Autowired
    AssemblersRepository assemblersRepository;

	public static void main(String[] args) {
		SpringApplication.run(StarDriveApplication.class, args);
		Database.init();
		System.out.println( "Hello World!" );
        ProductionService prod = new ProductionService();
        AssemblyLine line = prod.createAssemblyLine();
        prod.addAssembler("eletronic", line);
        prod.addAssembler("batteries", line);
        prod.addAssembler("chasis", line);
        prod.addAssembler("painter", line);
        line.startAssembling();

	}

}
