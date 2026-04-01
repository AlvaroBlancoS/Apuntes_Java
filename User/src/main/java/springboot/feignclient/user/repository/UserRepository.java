package springboot.feignclient.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.feignclient.user.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByName(String name);

}
