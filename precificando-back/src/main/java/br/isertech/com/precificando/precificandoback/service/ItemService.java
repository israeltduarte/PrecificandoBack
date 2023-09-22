package br.isertech.com.precificando.precificandoback.service;

import br.isertech.com.precificando.precificandoback.constants.Messages;
import br.isertech.com.precificando.precificandoback.dto.ItemDTO;
import br.isertech.com.precificando.precificandoback.entity.Item;
import br.isertech.com.precificando.precificandoback.entity.Stock;
import br.isertech.com.precificando.precificandoback.error.exception.ItemNotFoundException;
import br.isertech.com.precificando.precificandoback.repository.ItemRepository;
import br.isertech.com.precificando.precificandoback.util.ItemTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final StockService stockService;

    public List<Item> getAllItems() {

        List<Item> items = itemRepository.findAll();
        log.info("ItemService - getAllItems() - List<Item>={}", items);

        return items;
    }

    public Item addItem(ItemDTO dto) {

        Item item = getItemEntityReady(dto);
        item = itemRepository.save(item);
        log.info("ItemService - addItem() - Item={}", item);

        return item;
    }

    private Item getItemEntityReady(ItemDTO dto) {

        Stock stock = stockService.getStockById(dto.getStockId());

        LocalDateTime now = LocalDateTime.now();
        Item item = ItemTransformer.fromDTO(dto);
        item.setStock(stock);
        item.setCreated(now);
        item.setUpdated(now);

        return item;
    }

    public Item getItemById(String id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Messages.ITEM_NOT_FOUND_INFO));
        log.info("ItemService - getItemById() - Item={}", item);

        return item;
    }

    public Item updateItemById(ItemDTO dto, String id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Messages.ITEM_NOT_FOUND_INFO));

        item.setUpdated(LocalDateTime.now());
        item.setDescription(dto.getDescription());
        item.setMeasurement(dto.getMeasurement());
        item.setInStock(dto.isInStock());

        itemRepository.save(item);

        log.info("ItemService - updateItemById() - Item={}", item);

        return item;
    }

    public void deleteItemById(String id) {

        itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Messages.ITEM_NOT_FOUND_INFO));
        itemRepository.deleteById(id);

        log.info("ItemService - deleteItemById() - ".concat(Messages.ITEM_DELETED));
    }

    public void deleteAllItems() {

        itemRepository.deleteAll();

        log.info("ItemService - deleteAllItems() - ".concat(Messages.ITEMS_DELETED));
    }
}
