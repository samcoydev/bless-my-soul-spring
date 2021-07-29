package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;

import java.util.List;

public interface ItemService {

    Item save(ItemDto item);
    Item update(long id, ItemDto item);

    List<Item> findAll();
    void delete(long id);

    Item findById(Long id);

}
