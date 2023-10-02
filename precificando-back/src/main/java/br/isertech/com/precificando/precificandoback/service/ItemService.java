package br.isertech.com.precificando.precificandoback.service;

import br.isertech.com.precificando.precificandoback.constants.Messages;
import br.isertech.com.precificando.precificandoback.dto.ItemDTO;
import br.isertech.com.precificando.precificandoback.entity.ITUser;
import br.isertech.com.precificando.precificandoback.entity.Item;
import br.isertech.com.precificando.precificandoback.error.exception.ItemNotFoundException;
import br.isertech.com.precificando.precificandoback.error.exception.UserNotFoundException;
import br.isertech.com.precificando.precificandoback.repository.ItemRepository;
import br.isertech.com.precificando.precificandoback.repository.UserRepository;
import br.isertech.com.precificando.precificandoback.util.ItemTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<Item> getAllItems() {

        List<Item> items = itemRepository.findAll();

        log.info("ItemService - getAllItems() - List<Item>={}", items);

        return items;
    }

    public List<Item> getAllItemsByUserId(String userId) {

        List<Item> items = itemRepository.findAllByUserId(userId);

        log.info("ItemService - getAllItemsById() - List<Item>={}", items);

        return items;
    }

    public Item addItem(ItemDTO dto) {

        ITUser user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO));

        log.info("ItemService - addItem() - ITUser={}", user);

        Item item = getItemEntityReady(dto, user);
        item = itemRepository.save(item);

        log.info("ItemService - addItem() - Item={}", item);

        return item;
    }

    private Item getItemEntityReady(ItemDTO dto, ITUser user) {

        LocalDateTime now = LocalDateTime.now();
        Item item = ItemTransformer.fromDTO(dto);
        item.setUser(user);
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

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Messages.ITEM_NOT_FOUND_INFO));
        itemRepository.delete(item);

        log.info("ItemService - deleteItemById() - ".concat(Messages.ITEM_DELETED));
    }

    public void deleteAllItems() {

        itemRepository.deleteAll();

        log.info("ItemService - deleteAllItems() - ".concat(Messages.ITEMS_DELETED));
    }
}
