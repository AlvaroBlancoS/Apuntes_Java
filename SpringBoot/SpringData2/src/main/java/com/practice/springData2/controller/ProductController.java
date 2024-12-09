package com.practice.springData2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.practice.springData2.domain.product.Product;
import com.practice.springData2.domain.product.ProductDto;
import com.practice.springData2.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping("/createv1")
	@Operation(description= "Create a product in version 1", summary="Create a product in version 1")
	public ResponseEntity<Product> createv1(@RequestBody @Valid Product product){
		Product createProduct = productService.create(product);
		return ResponseEntity.ok(createProduct);
	}
	
	@PostMapping("/createv2")
	@Operation(description= "Create a product in version 2", summary="Create a product in version 2")
	public ResponseEntity<ProductDto> createv2(@RequestBody @Valid ProductDto productDto){
		ProductDto createProduct = productService.createv2(productDto);
		return ResponseEntity.ok(createProduct);
	}
	
	@PostMapping("/createv3")
	@Operation(description= "Create a product in version 3", summary="Create a product in version 3")
	public ResponseEntity<ProductDto> createv3(@RequestBody @Valid ProductDto productDto){
		ProductDto createProduct = productService.createv3(productDto);
		return ResponseEntity.ok(createProduct);
	}
	
	
	@GetMapping("/getProduct/by/{nameProduct}/version 1")
	@Operation(description= "Get a product by name in version 1", summary="Get a product by name in version 1")
	public ResponseEntity<Product> getProductByNameV1(@PathVariable("nameProduct") String nameProduct){
		Product product = productService.getProduct(nameProduct);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/getProduct/by/{nameProduct}/version 2")
	@Operation(description= "Get a product by name in version 2", summary="Get a product by name in vweaion 2")
	public ResponseEntity<Product> getProductByNameV2(@PathVariable("nameProduct") String nameProduct){
		Product product = productService.getProductV2(nameProduct);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/getProduct/by/{nameProduct}/version 3")
	@Operation(description= "Get a product by name in version 3", summary="Get a product by name in vweaion 3")
	public ResponseEntity<ProductDto> getProductByNameV3(@PathVariable("nameProduct") String nameProduct){
		ProductDto product = productService.getProductV3(nameProduct);
		return ResponseEntity.ok(product);
			
	}
	
	@GetMapping("/get-all-Products")
	@Operation(description= "Get all products", summary="Get all products") 
	public ResponseEntity<List<Product>>getAllProducts(){
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping("/get-all-Products/version 2")
	@Operation(description= "Get all products in version 2", summary="Get all products in version 2") 
	public ResponseEntity<List<ProductDto>>getAllProductsV2(){
		return ResponseEntity.ok(productService.getAllProductsV2());
	}
	
	@GetMapping("/get-all-Products/version 3")
	@Operation(description= "Get all products in version 3", summary="Get all products in version 3") 
	public ResponseEntity<List<ProductDto>>getAllProductsV3(){
		return ResponseEntity.ok(productService.getAllProductsV3());
	}
	
	@GetMapping("/get-all-Products/version 4")
	@Operation(description= "Get all products in version 4", summary="Get all products in version 4") 
	public ResponseEntity<List<ProductDto>>getAllProductsV4(@RequestParam(value="price", required =false) Double price){
		return ResponseEntity.ok(productService.getAllProductsV4(price));
	}
	
	
	@PutMapping("/update-product/{nameProduct}")
	@Operation(description="Update a product by name", summary="Update a product by name")
	public ResponseEntity<Product> updateProduct(@PathVariable("nameProduct") String nameProduct, @Valid @RequestBody Product product){
		 Product updatedProduct = productService.update(nameProduct, product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@PutMapping("/update-product/{nameProduct}/v2")
	@Operation(description="Update a product by name", summary="Update a product by name")
	public ResponseEntity<ProductDto> updateProductV2(@PathVariable("nameProduct") String nameProduct, @Valid @RequestBody ProductDto productDto){
			ProductDto updateProductDto = productService.updatev2(nameProduct, productDto);
		return ResponseEntity.ok(updateProductDto);
	}
	
	
	@DeleteMapping("delete/{id}")
	@Operation(description="Delete a product by id", summary ="Delete a product by id")
	public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id){
		productService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("delete/{nameProduct}")
	@Operation(description="Delete a product by name", summary ="Delete a product by name")
	public ResponseEntity<Void> deleteProductByName(@PathVariable("nameProduct") String nameProduct){
		productService.deleteByNameProduct(nameProduct);
		return ResponseEntity.noContent().build();
	}
	

}
