package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.CartItemDao;
import blessmysoulbackend.rest.dao.ItemDao;
import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Autowired
    CartItemDao cartItemDao;

    @CachePut("items")
    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();
        itemDao.findByOrderById().iterator().forEachRemaining(itemList::add);
        return itemList;
    }

    public List<Item> findAllExcludingIsDeleted() {
        List<Item> itemList = new ArrayList<>();
        itemDao.findByIsDeletedFalse().iterator().forEachRemaining(itemList::add);
        return itemList;
    }

    public List<Item> findFeaturedItems() {
        List<Item> itemList = new ArrayList<>();
        itemDao.findByIsFeaturedTrueAndIsDeletedFalse().iterator().forEachRemaining(itemList::add);
        return itemList;
    }

    public List<Item> findNewestItems() {
        List<Item> itemList = new ArrayList<>();
        itemDao.findTop4ByOrderByIdDescAndIsDeletedFalse().iterator().forEachRemaining(itemList::add);
        return itemList;
    }

    @Override
    public Item findById(Long id) {
        return itemDao.findById(id).get();
    }

    @Override
    public List<Item> findByCategoryID(Long categoryId) {
        List<Item> itemList = new ArrayList<>();
        itemDao.findByOrderById().iterator().forEachRemaining(item -> {
                if (item.getCategory().getId() == categoryId)
                    itemList.add(item);
            }
        );
        return itemList;
    }

    public Item save(ItemDto item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setPrice(item.getPrice());
        newItem.setDescription(item.getDescription());
        newItem.setCategory(item.getCategory());
        newItem.setState(item.getState());
        newItem.setFeatured(item.isFeatured());
        newItem.setDeleted(item.isDeleted());
        newItem.setImage(item.getImage());

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
            existingItem.setState(item.getState());;
            existingItem.setFeatured(item.isFeatured());
            existingItem.setDeleted(item.isDeleted());
            existingItem.setImage(item.getImage());

            return itemDao.save(existingItem);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Item item = itemDao.findById(id).get();
        item.setDeleted(true);
        // For Hard Delete (BROKEN) - itemDao.delete(item);
    }

}
