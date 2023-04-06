package com.ChinaMaal.ECommerce.Repository;

import com.ChinaMaal.ECommerce.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByPanNo(String panNo);
    List<Seller> findByName(String name);
}
