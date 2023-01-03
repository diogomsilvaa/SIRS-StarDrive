package pt.sirs.app.StarDrive.production.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pt.sirs.app.StarDrive.production.domain.Assembler;

@Repository
public interface AssemblersRepository extends MongoRepository<Assembler, String> {
    
}
