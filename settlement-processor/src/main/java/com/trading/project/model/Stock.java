package com.trading.project.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Stock {
    private String id;
    private String name;
    private BigDecimal price;
}
