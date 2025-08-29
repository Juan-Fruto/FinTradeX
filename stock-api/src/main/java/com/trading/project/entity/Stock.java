package com.trading.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "stocks")
@Data
public class Stock {
    @Id
    @GeneratedValue
    @Column(name = "stock_id")
    private String id;

    @Column(name = "stock_name")
    private String name;

    private BigDecimal price;
}
