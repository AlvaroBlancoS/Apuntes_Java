package com.practice.springData2.domain.user;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spring_data_2_user")
public class User {
	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence"

	)
	private Long id;
	
	@Column(name= "username")
	private String username;
	
	@Column(name= "password")
	private String password;
	
}
