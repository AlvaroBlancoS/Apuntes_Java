package com.practice.springData2.domain.product;

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
@Table(name = "spring_data_2_product")
public class Product {
	@Id
	@SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1, initialValue = 999)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence"

	)
	private Long id;
	
	@Column(name="name_product")
	private String nameProduct;
	
	@Column(name="price_product")
	private Double price;
	
	@Column(name="origin_product")
	private String origenProduct;
	
}
