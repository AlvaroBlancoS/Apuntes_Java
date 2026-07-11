package springboot.repository;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.entity.Mail;

@Repository
public interface EmailRepository extends JpaRepository <Mail, UUID> {


    Optional<Mail> findByMail(String mail);
    Optional<Mail> findByName(String name);

}
