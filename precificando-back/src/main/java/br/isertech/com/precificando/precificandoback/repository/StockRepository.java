package br.isertech.com.precificando.precificandoback.repository;

import br.isertech.com.precificando.precificandoback.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
