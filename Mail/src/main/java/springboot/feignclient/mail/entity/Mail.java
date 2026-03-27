package springboot.feignclient.mail.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "feign_client_user", schema = "feign_client")
public class Mail {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
    private UUID id;
    private String mail;
    private String description;

    public Mail() {
    }

    public Mail(UUID id, String mail, String description) {
        this.id = id;
        this.mail = mail;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
