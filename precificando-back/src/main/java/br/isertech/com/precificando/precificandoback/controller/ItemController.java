package br.isertech.com.precificando.precificandoback.controller;

import br.isertech.com.precificando.precificandoback.dto.ItemDTO;
import br.isertech.com.precificando.precificandoback.entity.Item;
import br.isertech.com.precificando.precificandoback.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {

        List<Item> items = itemService.getAllItems();
        if (!items.isEmpty()) {
            for (Item item : items) {
                item.add(linkTo(methodOn(ItemController.class).getItemById(item.getId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {

        Item item = itemService.getItemById(id);

        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@Valid @RequestBody ItemDTO dto) {

        Item item = itemService.addItem(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItemById(@Valid @RequestBody ItemDTO dto, @PathVariable String id) {

        Item item = itemService.updateItemById(dto, id);

        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable String id) {

        itemService.deleteItemById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllItems() {

        itemService.deleteAllItems();

        return ResponseEntity.ok().build();
    }
}