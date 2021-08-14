package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        System.out.println("[GET] All Items");
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item getItemByID(@PathVariable Long id) {
        System.out.println("[GET] Item with ID: " + id);
        return itemService.findById(id);
    }

    @PostMapping
    public Item saveItem(@Valid @RequestBody ItemDto item) {
        System.out.println("[POST] " + item.getName());
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @Valid @RequestBody ItemDto item) {
        System.out.println("[PUT] " + item.getName());
        return itemService.update(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        System.out.println("[DELETE] Item: " + id);
        itemService.delete(id);
    }

}
