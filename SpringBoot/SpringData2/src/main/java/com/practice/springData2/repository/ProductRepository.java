package com.practice.springData2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice.springData2.domain.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByNameProduct(String nameProduct);

	List<Product> findByPrice(Double price);

	boolean existsByNameProduct(String nameProduct);

	@Query("DELETE FROM Product p WHERE p.nameProduct =:findNameProduct")
	void deleteByNameProduct(@Param("findNameProduct") String nameProduct);
}
