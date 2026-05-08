package springboot.resttemplate.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.resttemplate.user.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByName(String name);
    
    Optional<User> findByMailId(UUID mailId);

}
