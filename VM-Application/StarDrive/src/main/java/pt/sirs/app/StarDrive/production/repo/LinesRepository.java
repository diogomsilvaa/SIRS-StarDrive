package pt.sirs.app.StarDrive.production.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pt.sirs.app.StarDrive.production.domain.AssemblyLine;

@Repository
public interface LinesRepository extends MongoRepository<AssemblyLine, String> {
    
}
