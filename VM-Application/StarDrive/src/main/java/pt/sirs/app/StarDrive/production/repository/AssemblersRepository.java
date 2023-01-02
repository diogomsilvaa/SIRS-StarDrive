package pt.sirs.app.StarDrive.production.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.sirs.app.StarDrive.production.domain.Assembler;

public interface AssemblersRepository extends MongoRepository<Assembler, String> {
    
}
