package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping
    public Item saveItem(@Valid @RequestBody ItemDto item) {
        System.out.println("[POST] " + item.getName());
        return itemService.save(item);
    }

}
