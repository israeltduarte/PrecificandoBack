package br.isertech.com.precificando.precificandoback.controller;

import br.isertech.com.precificando.precificandoback.dto.StockDTO;
import br.isertech.com.precificando.precificandoback.dto.UserDTO;
import br.isertech.com.precificando.precificandoback.entity.Stock;
import br.isertech.com.precificando.precificandoback.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {

        List<Stock> stocks = stockService.getAllStocks();
        if (!stocks.isEmpty()) {
            for (Stock stock : stocks) {
                stock.add(linkTo(methodOn(StockController.class).getUserById(stock.getId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(stocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getUserById(@PathVariable String id) {

        Stock stock = stockService.getStockById(id);
        return ResponseEntity.status(HttpStatus.OK).body(stock);
    }

    @PostMapping
    public ResponseEntity<Stock> addStock(@Valid @RequestBody StockDTO dto) {

        Stock stock = stockService.addStock(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateUserById(@Valid @RequestBody UserDTO dto, @PathVariable String id) {

        Stock stock = stockService.updateStockById(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(stock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockById(@PathVariable String id) {

        stockService.deleteStockById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {

        stockService.deleteAllStocks();
        return ResponseEntity.ok().build();
    }
}