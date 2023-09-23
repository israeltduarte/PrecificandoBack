package br.isertech.com.precificando.precificandoback.service;

import br.isertech.com.precificando.precificandoback.constants.Messages;
import br.isertech.com.precificando.precificandoback.dto.StockDTO;
import br.isertech.com.precificando.precificandoback.dto.UserDTO;
import br.isertech.com.precificando.precificandoback.entity.ITUser;
import br.isertech.com.precificando.precificandoback.entity.Item;
import br.isertech.com.precificando.precificandoback.entity.Stock;
import br.isertech.com.precificando.precificandoback.error.exception.StockNotFoundException;
import br.isertech.com.precificando.precificandoback.repository.StockRepository;
import br.isertech.com.precificando.precificandoback.util.ItemTransformer;
import br.isertech.com.precificando.precificandoback.util.StockTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final UserService userService;

    public List<Stock> getAllStocks() {

        List<Stock> stocks = stockRepository.findAll();
        log.info("StockService - getAllStocks() - List<Stock>={}", stocks);

        return stocks;
    }

    public Stock addStock(StockDTO dto) {

        ITUser user = userService.getUserById(dto.getUserId());
        Stock stock = getStockEntityReady(dto, user);
        stock = stockRepository.save(stock);
        log.info("StockService - addStock() - Stock={}", stock);

        return stock;
    }

    private Stock getStockEntityReady(StockDTO dto, ITUser user) {

        List<Item> items = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        Stock stock = StockTransformer.fromDTO(dto);

        dto.getItems().forEach(itemDTO -> {
            Item item = ItemTransformer.fromDTO(itemDTO);
            item.setCreated(now);
            item.setUpdated(now);
            item.setStock(stock);
            items.add(item);
        });

        stock.setItems(items);
        stock.setUser(user);
        stock.setCreated(now);
        stock.setUpdated(now);

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
        log.info("StockService - deleteStockById() - ".concat(Messages.STOCK_DELETED));
    }

    public void deleteAllStocks() {

        stockRepository.deleteAll();
        log.info("StockService - deleteAllStocks() - ".concat(Messages.STOCKS_DELETED));
    }
}
