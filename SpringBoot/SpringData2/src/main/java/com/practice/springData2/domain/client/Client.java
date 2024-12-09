package com.practice.springData2.domain.client;

import java.util.HashSet;
import java.util.Set;

import com.practice.springData2.domain.address.Address;
import com.practice.springData2.domain.product.Product;
import com.practice.springData2.domain.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.JoinColumn;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spring_data_2_client")
public class Client {
	@Id
	@SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1, initialValue = 999)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence"

	)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="Surnames")
	private String apellidos;
	
	@Column(name="document")
	private String document;
	
	@OneToOne
	private User user;
	
	@OneToMany(fetch= FetchType.EAGER,
				cascade = CascadeType.ALL
			)
	private Set <Address> addresses = new HashSet<>();
	
	@ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name = "client_product",
            joinColumns = @JoinColumn(name = "fk_client"),
            inverseJoinColumns = @JoinColumn(name = "fk_product")
        )
	private Set<Product> products = new HashSet<>();
	
}
