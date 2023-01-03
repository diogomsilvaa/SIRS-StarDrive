package pt.sirs.app.StarDrive.shiftManager.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pt.sirs.app.StarDrive.shiftManager.domain.Shift;

@Repository
public interface ShiftRepository extends MongoRepository<Shift, String>{

}
