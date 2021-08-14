package blessmysoulbackend.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import blessmysoulbackend.rest.dao.ItemDao;
import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ItemService;

@Service(value = "itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Cacheable(value="items")
    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();
        itemDao.findByOrderById().iterator().forEachRemaining(itemList::add);
        return itemList;
    }

    @Override
    @CacheEvict(cacheNames = { "evaluations"}, allEntries=true)
    public void delete(long id) {
        Item item = itemDao.findById(id).get();
        itemDao.delete(item);
    }

    @Override
    @Cacheable(value="items")
    public Item findById(Long id) {
        return itemDao.findById(id).get();
    }

    @CachePut(cacheNames = { "evaluations"})
    public Item save(ItemDto item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setPrice(item.getPrice());
        newItem.setDescription(item.getDescription());
        newItem.setState(item.getState());

        itemDao.save(newItem);

        return itemDao.save(newItem);
    }

    @CacheEvict(cacheNames = { "evaluations"}, allEntries=true)
    public Item update(long id, ItemDto item) {
        Optional<Item> optionalItem = itemDao.findById(id);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();
            existingItem.setName(item.getName());
            existingItem.setPrice(item.getPrice());
            existingItem.setDescription(item.getDescription());
            existingItem.setState(item.getState());

            return itemDao.save(existingItem);
        } else {
            return null;
        }
    }

}
