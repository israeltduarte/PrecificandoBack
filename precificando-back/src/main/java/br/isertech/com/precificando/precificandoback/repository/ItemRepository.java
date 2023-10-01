package br.isertech.com.precificando.precificandoback.repository;

import br.isertech.com.precificando.precificandoback.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    List<Item> findAllByUserId(String userId);

}
