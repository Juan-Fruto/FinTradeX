package com.trading.project.service;

import com.trading.project.entity.Stock;
import com.trading.project.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockRepository stockRepository;

    private final Logger logger;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
        this.logger = Logger.getLogger(StockService.class.getName());
    }

    public Stock getStockById(String id) {
        return stockRepository.findById(id).orElse(null);
    }

    public List<Stock> getAll() {
        return stockRepository.findAll();
    }
}
