package com.practice.springData2.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.practice.springData2.domain.product.Product;
import com.practice.springData2.domain.product.ProductDto;
import com.practice.springData2.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public Product create(final Product product) {
		Optional<Product> newProduct = productRepository.findByNameProduct(product.getNameProduct());
		if (newProduct.isPresent()) {
			new RuntimeException(product.getNameProduct() + " exist");
		}
//		Product savedProduct = newProduct.get();
//		return productRepository.save(savedProduct);

		return productRepository.save(product);
	}

	@Override
	public ProductDto createv2(final ProductDto productDto) {
		boolean exists = productRepository.findByNameProduct(productDto.getNameProduct()).isPresent();
		if (exists) {
			throw new RuntimeException(productDto.getNameProduct() + " exist");
		}
		Product product = mapToEntity(productDto, new Product());
		Product savedProduct = productRepository.save(product);
		return mapToDto(savedProduct, new ProductDto());
	}

	@Override
	public ProductDto createv3(final ProductDto productDto) {
		productRepository.findByNameProduct(productDto.getNameProduct()).ifPresent(p -> {
			throw new RuntimeException(productDto.getNameProduct() + " exist");
		});
		Product product = mapToEntity(productDto, new Product());
		Product savedProduct = productRepository.save(product);
		return mapToDto(savedProduct, new ProductDto());
	}

	@Override
	public Product getProduct(final String nameProduct) {
		Optional<Product> product = productRepository.findByNameProduct(nameProduct);
		if (!product.isPresent()) {
			new RuntimeException(nameProduct + " Not exist");
		}
		return product.get();
	}

	@Override
	public Product getProductV2(final String nameProduct) {
		return productRepository.findByNameProduct(nameProduct)
				.orElseThrow(() -> new RuntimeException(nameProduct + " Not exist"));
	}

	@Override
	public ProductDto getProductV3(final String nameProduct) {
		return productRepository.findByNameProduct(nameProduct).map(product -> mapToDto(product, new ProductDto()))
				.orElseThrow(() -> new RuntimeException(nameProduct + " not exist"));
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<ProductDto> getAllProductsV2() {
		List<Product> seeProducts = productRepository.findAll();
		List<ProductDto> addProducts = new ArrayList<>();
		for (Product product : seeProducts) {
			addProducts.add(mapToDto(product, new ProductDto()));
		}
		return addProducts;
	}

	@Override
	public List<ProductDto> getAllProductsV3() {
		List<Product> seeProducts = productRepository.findAll();
		return seeProducts.stream().map(product -> mapToDto(product, new ProductDto())).toList();
	}

	@Override
	public List<ProductDto> getAllProductsV4(final Double price) {
		List<Product> seeProducts;
		if (price != null) {
			seeProducts = productRepository.findByPrice(price);
		} else {
			seeProducts = productRepository.findAll(Sort.by("id"));
		}
		return seeProducts.stream().map(product -> mapToDto(product, new ProductDto())).toList();
	}

	@Override
	public Product update(final String nameProduct, final Product product) {
		Optional<Product> updateProduct = productRepository.findByNameProduct(nameProduct);
		if (updateProduct.isPresent()) {
			Product addProduct = updateProduct.get();
			addProduct.setNameProduct(product.getNameProduct());
			addProduct.setOrigenProduct(product.getOrigenProduct());
			addProduct.setPrice(product.getPrice());
			return productRepository.save(addProduct);
		} else {
			throw new RuntimeException(nameProduct + " not exist");
		}
	}

	@Override
	public ProductDto updatev2(final String nameProduct, final ProductDto productDto) {
		final Product updateProduct = productRepository.findByNameProduct(nameProduct)
				.orElseThrow(()-> new RuntimeException(nameProduct+" not exist"));
		mapToEntity(productDto, updateProduct);
		Product addProduct = productRepository.save(updateProduct);
		return mapToDto(addProduct, productDto);
	}

	@Override
	public void deleteById(final Long id) {
		if (existIdProduct(id)) {
			productRepository.deleteById(id);
		} else {
			throw new RuntimeException(id+" not exist");
		}
	}
	
	private boolean existIdProduct(final Long id) {
		return productRepository.existsById(id);
	}

	@Override
	public void deleteByNameProduct(final String nameProduct) {
		if (existNameProduct(nameProduct)) {
			productRepository.deleteByNameProduct(nameProduct);
		} else {
			throw new RuntimeException(nameProduct+" not exist");
		}

	}
	
	private boolean existNameProduct(final String nameProduct) {
		return productRepository.existsByNameProduct(nameProduct);
	}
	
	public ProductDto mapToDto(final Product product, final ProductDto productDto) {
		productDto.setNameProduct(product.getNameProduct());
		productDto.setPrice(product.getPrice());
		productDto.setOrigenProduct(product.getOrigenProduct());
		return productDto;
	}
	
	public Product mapToEntity(final ProductDto productDto, final Product product) {
		product.setNameProduct(productDto.getNameProduct());
		product.setPrice(productDto.getPrice());
		product.setOrigenProduct(productDto.getOrigenProduct());
		return product;
	}
}
