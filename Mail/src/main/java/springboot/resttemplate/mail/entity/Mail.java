package springboot.resttemplate.mail.entity;

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
@Table(name = "mail", schema = "rest_template")
public class Mail {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(length = 300)
    private String description;
}
