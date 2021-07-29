package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.ItemDao;
import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();
        itemDao.findByOrderById().iterator().forEachRemaining(itemList::add);
        return itemList;
    }

    @Override
    public void delete(long id) {
        Item item = itemDao.findById(id).get();
        itemDao.delete(item);
    }

    @Override
    public Item findById(Long id) {
        return itemDao.findById(id).get();
    }

    public Item save(ItemDto item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setPrice(item.getPrice());
        newItem.setDescription(item.getDescription());

        itemDao.save(newItem);

        return itemDao.save(newItem);
    }

    public Item update(long id, ItemDto item) {
        Optional<Item> optionalItem = itemDao.findById(id);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();
            existingItem.setName(item.getName());
            existingItem.setPrice(item.getPrice());
            existingItem.setDescription(item.getDescription());

            return itemDao.save(existingItem);
        } else {
            return null;
        }
    }

}
