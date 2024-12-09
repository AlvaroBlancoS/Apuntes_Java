package com.practice.springData2;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import com.practice.springData2.domain.product.Product;
import com.practice.springData2.domain.product.ProductDto;
import com.practice.springData2.repository.ProductRepository;
import com.practice.springData2.service.product.ProductServiceImpl;

@SpringBootTest
class SpringData2ApplicationTests {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	private Product product;

	private static final String PRODUCT_NAME = "Teclado";
	private static final String PRODUCT_ORIGIN = "China";
	private static final double PRODUCT_PRICE = 2.00;

	@BeforeEach
	void setUp() {
		product = entityProduct();
	}

//	@Test
//	void testCreateProduct_WhenProductDoesNotExistV1() {
//		// Configurar repositorio
//		when(productRepository.existsByNameProduct(product.getNameProduct())).thenReturn(false);
//		when(productRepository.save(product)).thenReturn(product);
//		
//		// Ejecutar el servicio
//		Product result = productService.create(product);
//		assertNotNull(result);
//		assertEquals(product.getNameProduct(), result.getNameProduct());
//		verify(productRepository, times(1)).save(product);
//
//	}
//
//	@Test
//	void testCreateProduct_WhenProductExistsV1() {
//		// Configurar repositorio
//		when(productRepository.existsByNameProduct(product.getNameProduct())).thenReturn(true);
//
//		// Ejecutar y verificar que lanza excepcion
//		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//			productService.create(product);
//
//		});
//
//		assertEquals("Product already exists", exception.getMessage());
//		verify(productRepository, never()).save(any());
//
//	}
//	
//
//	@Test
//	void testCreateProduct_WhenProductDoesNotExistV2() {
//		// Configurar repositorio
//		when(productRepository.existsByNameProduct(product.getNameProduct())).thenReturn(false);
//		when(productRepository.save(product)).thenReturn(product);
//		
//		// Ejecutar el servicio
//		ProductDto convertToDto = productService.mapToDto(product, new ProductDto());
//		ProductDto result = productService.createv2(convertToDto);
//		assertNotNull(result);
//		assertEquals(product.getNameProduct(), result.getNameProduct());
//		verify(productRepository, times(1)).save(product);
//
//	}
//	
//	@Test
//	void testCreateProduct_WhenProductExistsV2() {
//		// Configurar repositorio
//		when(productRepository.existsByNameProduct(product.getNameProduct())).thenReturn(true);
//		// Ejecutar y verificar que lanza excepcion
//		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//			ProductDto convertToDto = productService.mapToDto(product, new ProductDto());
//			productService.createv2(convertToDto);
//
//		});
//
//		assertEquals("Product already exists", exception.getMessage());
//		verify(productRepository, never()).save(any());
//
//	}
//	
//	@Test
//	void testCreateProduct_WhenProductDoesNotExistV3() {
//		// Configurar repositorio
//		when(productRepository.existsByNameProduct(product.getNameProduct())).thenReturn(false);
//		when(productRepository.save(product)).thenReturn(product);
//		
//		// Ejecutar el servicio
//		ProductDto convertToDto = productService.mapToDto(product, new ProductDto());
//		ProductDto result = productService.createv3(convertToDto);
//		assertNotNull(result);
//		assertEquals(product.getNameProduct(), result.getNameProduct());
//		verify(productRepository, times(1)).save(product);
//
//	}
//	
//	@Test
//	void testCreateProduct_WhenProductExistsV3() {
//		// Configurar repositorio
//		when(productRepository.existsByNameProduct(product.getNameProduct())).thenReturn(true);
//		// Ejecutar y verificar que lanza excepcion
//		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//			ProductDto convertToDto = productService.mapToDto(product, new ProductDto());
//			productService.createv3(convertToDto);
//
//		});
//
//		assertEquals("Product already exists", exception.getMessage());
//		verify(productRepository, never()).save(any());
//
//	}
//
//	@Test
//	void testGetProductByNameProductSuccessV1() {
//		String name = "teclado";
//		// Configurar el repositorio;
//		when(productRepository.findByNameProduct(name)).thenReturn(Optional.of(product));
//
//		// Ejecutar el servicio
//		Product result = productService.getProduct(name);
//
//		// verificar
//		assertNotNull(result);
//		assertEquals(product.getNameProduct(), result.getNameProduct());
//		verify(productRepository, times(1)).findByNameProduct(name);
//
//	}
//
//	@Test
//	void testGetProductByNameProductNotFoundV1() {
//		String name = "teclado";
//		// Configurar el repositorio;
//		when(productRepository.findByNameProduct(name)).thenReturn(Optional.empty());
//
//		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//			productService.getProduct(name);
//		});
//
//		assertEquals("Product not found", exception.getMessage());
//		verify(productRepository, times(1)).findByNameProduct(name);
//	}
//
//	@Test
//	void testUpdateProductSuccessV1() {
//		// Configurar el repositorio
//		String nameOld = "teclado";
//		String nameNew = "mouse";
//		Product updateProduct = new Product();
//		updateProduct.setNameProduct(nameNew);
//		updateProduct.setPrice(300.0);
//		updateProduct.setOrigenProduct("Japan");
//		when(productRepository.findByNameProduct("teclado")).thenReturn(Optional.of(product));
//		when(productRepository.save(any(Product.class))).thenReturn(updateProduct);
//
//		// Ejecutar el servicio
//		Product result = productService.update(nameOld, updateProduct);
//
//		// Verificar
//		assertNotNull(result);
//		assertEquals(nameNew, result.getNameProduct());
//		assertEquals(300.0, result.getPrice());
//		verify(productRepository, times(1)).save(product);
//
//	}
//
//	@Test
//	void testDeleteProductSuccessV2() {
//		String name = PRODUCT_NAME;
//		// Configurar el repositorio
//		when(productRepository.existsByNameProduct(name)).thenReturn(true);
//
//		// Ejecutar el servicio
//		productService.deleteByNameProduct(name);
//
//		// Verificar
//		verify(productRepository, times(1)).deleteByNameProduct(name);
//	}
//
//	@Test
//	void testDeleteProductNotFoundV2() {
//		String name = "mouse";
//		// Configurar el repositorio
//		when(productRepository.existsByNameProduct(name)).thenReturn(false);
//
//		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//			productService.deleteByNameProduct(name);
//		});
//
//		assertEquals("Product not found", exception.getMessage());
//		verify(productRepository, never()).deleteByNameProduct(name);
//
//	}
	
	
	@Test
	void testMapToDto() {
		Product p = entityProduct();
		ProductDto result = productService.mapToDto(p, new ProductDto());

		assertNotNull(result);// Asegurar que el objeto no es nulo
		assertEquals(result.getNameProduct(), PRODUCT_NAME);// Verificar nombre del producto
		assertEquals(result.getPrice(), PRODUCT_PRICE);// verificar precio
		assertEquals(result.getOrigenProduct(), PRODUCT_ORIGIN);// verifica origen

	}

	@Test
	void testMapToEntity() {
		ProductDto productDto = dtoProduct();
		Product result = productService.mapToEntity(productDto, new Product());

		assertNotNull(result);
		assertEquals(PRODUCT_NAME, result.getNameProduct());
		assertEquals(PRODUCT_PRICE, result.getPrice());
		assertEquals(PRODUCT_ORIGIN, result.getOrigenProduct());

	}



	private Product entityProduct() {
		Product p = new Product();
		p.setNameProduct(PRODUCT_NAME);
		p.setPrice(PRODUCT_PRICE);
		p.setOrigenProduct(PRODUCT_ORIGIN);
		return p;
	}

	private ProductDto dtoProduct() {
		ProductDto p = new ProductDto();
		p.setNameProduct(PRODUCT_NAME);
		p.setPrice(PRODUCT_PRICE);
		p.setOrigenProduct(PRODUCT_ORIGIN);
		return p;
	}

}
