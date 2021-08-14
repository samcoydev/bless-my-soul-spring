package blessmysoulbackend.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ItemService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/item")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        if(log.isDebugEnabled()) log.debug("[GET] All Items");
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item getItemByID(@PathVariable Long id) {
    	if(log.isDebugEnabled()) log.debug("[GET] Item with ID: " + id);
        return itemService.findById(id);
    }

    @PostMapping
    public Item saveItem(@Valid @RequestBody ItemDto item) {
        if(log.isDebugEnabled()) log.debug("[POST] " + item.getName());
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@Valid @RequestBody ItemDto item, @PathVariable Long id) {
        if(log.isDebugEnabled()) log.debug("[PUT] " + item.getName());
        return itemService.update(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        if(log.isDebugEnabled()) log.debug("[DELETE] Item: " + id);
        itemService.delete(id);
    }

}
