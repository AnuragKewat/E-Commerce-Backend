package com.ChinaMaal.ECommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int requiredQuantity;
    @OneToOne
    @JoinColumn
    private Product product;
    @ManyToOne
    @JoinColumn
    private Cart cart;
    @ManyToOne
    @JoinColumn
    private Ordered order;
}
