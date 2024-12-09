package com.practice.springData2.service.product;

import java.util.List;

import com.practice.springData2.domain.product.Product;
import com.practice.springData2.domain.product.ProductDto;

public interface ProductService {

	public Product create(final Product product);

	public ProductDto createv2(final ProductDto product);
	
	public ProductDto createv3(final ProductDto productDto);
		
	public Product getProduct(final String nameProduct);
	
	public Product getProductV2(final String nameProduct);
	
	public ProductDto getProductV3(final String nameProduct);
	
	public List<Product> getAllProducts();
	
	public List<ProductDto> getAllProductsV2();
	
	public List<ProductDto> getAllProductsV3();
	
	public List<ProductDto> getAllProductsV4(final Double price);
	
	public Product update(final String nameProduct, final Product product);
	
	public ProductDto updatev2(final String nameProduct, final ProductDto productDto);
	
	public void deleteById(final Long id);
	
	public void deleteByNameProduct(final String nameProduct);
	
}
