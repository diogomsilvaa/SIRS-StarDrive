package pt.sirs.app.StarDrive.production.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.sirs.app.StarDrive.production.domain.AssemblyLine;

public interface LinesRepository extends MongoRepository<AssemblyLine, String> {
    
}
