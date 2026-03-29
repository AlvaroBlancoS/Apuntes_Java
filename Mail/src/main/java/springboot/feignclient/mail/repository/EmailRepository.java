package springboot.feignclient.mail.repository;
import java.util.Optional;
import java.util.UUID;

import  springboot.feignclient.mail.entity.Mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository <Mail, UUID> {


    Optional<Mail> findByMail(String mail);

}
