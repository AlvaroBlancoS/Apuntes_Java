package com.practice.springData2.domain.address;

import com.practice.springData2.domain.client.Client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spring_data_2_address")
public class Address {
	@Id
	@SequenceGenerator(
			name = "address_sequence", 
			sequenceName = "address_sequence", 
			allocationSize = 1, 
			initialValue = 8100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence"

	)
	private Long id;
	
	@Column(name ="street")
	private String street;
	
	@Column(name="number")
	private String number;
	
	@ManyToOne(
			fetch= FetchType.LAZY,
			optional = false)
	@JoinColumn(name="client_id")
	private Client client;
	
	

}
