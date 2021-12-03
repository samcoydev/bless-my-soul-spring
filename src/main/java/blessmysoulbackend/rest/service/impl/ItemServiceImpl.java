package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.ItemDao;
import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.CartItem;
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
    public Item findById(Long id) {
        return itemDao.findById(id).get();
    }

    @Override
    public List<Item> findByCategoryID(long categoryId) {
        List<Item> itemList = new ArrayList<>();
        itemDao.findByOrderById().iterator().forEachRemaining(item -> {
            if (item.getCategory().getId() == categoryId)
                itemList.add(item);
        });
        return itemList;
    }

    public Item save(ItemDto item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setPrice(item.getPrice());
        newItem.setDescription(item.getDescription());
        newItem.setCategory(item.getCategory());
        newItem.setState(item.getState());

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
            existingItem.setCategory(item.getCategory());
            existingItem.setState(item.getState());

            return itemDao.save(existingItem);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        Item item = itemDao.findById(id).get();
        itemDao.delete(item);
    }

}
