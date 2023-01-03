package pt.sirs.app.StarDrive.user.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pt.sirs.app.StarDrive.user.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
}
