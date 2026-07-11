package springboot.security.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.entity.User;

@Repository
public interface TokenUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByName(String name);
}
