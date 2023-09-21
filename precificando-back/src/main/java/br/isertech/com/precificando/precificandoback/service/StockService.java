package br.isertech.com.precificando.precificandoback.service;

import br.isertech.com.precificando.precificandoback.constants.Messages;
import br.isertech.com.precificando.precificandoback.dto.StockDTO;
import br.isertech.com.precificando.precificandoback.dto.UserDTO;
import br.isertech.com.precificando.precificandoback.entity.Stock;
import br.isertech.com.precificando.precificandoback.error.exception.StockNotFoundException;
import br.isertech.com.precificando.precificandoback.repository.StockRepository;
import br.isertech.com.precificando.precificandoback.util.StockTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;


    public List<Stock> getAllStocks() {

        List<Stock> stocks = stockRepository.findAll();
        log.info("StockService - getAllStocks() - List<Stock>={}", stocks);

        return stocks;
    }

    public Stock addStock(StockDTO dto, String userId) {

        Stock stock = StockTransformer.fromDTO(dto);

        stock = stockRepository.save(stock);

        log.info("StockService - addStock() - Stock = {}", stock);

        return stock;
    }

    public Stock getStockById(String id) {

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(Messages.STOCK_NOT_FOUND_INFO));
        log.info("StockService - getStockById() - Stock={}", stock);

        return stock;
    }

    public Stock updateStockById(UserDTO dto, String id) {

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(Messages.STOCK_NOT_FOUND_INFO));

        log.info("StockService - updateStockById() - Stock={}", stock);

        return stock;
    }

    public void deleteStockById(String id) {

        stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(Messages.STOCK_NOT_FOUND_INFO));
        stockRepository.deleteById(id);
        log.info("StockService - deleteStockById() - ".concat(Messages.USER_DELETED));
    }

    public void deleteAllStocks() {

        stockRepository.deleteAll();
        log.info("StockService - deleteAllStocks() - ".concat(Messages.STOCKS_DELETED));
    }
}
