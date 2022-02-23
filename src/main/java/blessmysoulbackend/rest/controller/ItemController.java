package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        log.info("[GET] All Items");
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item getItemByID(@PathVariable Long id) {
        log.info("[GET] Item with ID: " + id);
        return itemService.findById(id);
    }

    @GetMapping("/by-category/{id}")
    public List<Item> getItemsByCategoryID(@PathVariable Long id) {
        log.info("[GET] Items with Category ID: " + id);
        return itemService.findByCategoryID(id);
    }

    @PostMapping
    public Item saveItem(@Valid @RequestBody ItemDto item) {
        log.info("[POST] Item: " + item.getName());
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @Valid @RequestBody ItemDto item) {
        log.info("[PUT] Item: " + item.getName());
        return itemService.update(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        log.info("[DELETE] Item with ID: " + id);
        itemService.delete(id);
    }

}
