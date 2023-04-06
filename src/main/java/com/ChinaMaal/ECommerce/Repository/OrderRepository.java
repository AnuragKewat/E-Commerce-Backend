package com.ChinaMaal.ECommerce.Repository;

import com.ChinaMaal.ECommerce.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered, Integer> {
}
