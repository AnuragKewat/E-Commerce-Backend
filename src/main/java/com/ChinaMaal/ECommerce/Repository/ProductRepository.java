package com.ChinaMaal.ECommerce.Repository;

import com.ChinaMaal.ECommerce.Enum.ProductCategory;
import com.ChinaMaal.ECommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByProductCategory(ProductCategory productCategory);
}
