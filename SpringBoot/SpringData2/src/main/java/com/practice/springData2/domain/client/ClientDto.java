package com.practice.springData2.domain.client;

import java.util.List;

import com.practice.springData2.domain.address.Address;
import com.practice.springData2.domain.product.Product;

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
public class ClientDto {
	private String name;
	private String document;
	private String username;
	private List<Address> addresses;
	private List<Product> products;
}
