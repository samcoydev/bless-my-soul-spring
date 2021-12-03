package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;

import java.util.List;

public interface ItemService {

    Item save(ItemDto item);
    Item update(long id, ItemDto item);
    void delete(long id);

    List<Item> findAll();

    Item findById(Long id);
    List<Item> findByCategoryID(long categoryId);

}
