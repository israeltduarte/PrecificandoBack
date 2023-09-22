package br.isertech.com.precificando.precificandoback.repository;

import br.isertech.com.precificando.precificandoback.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
