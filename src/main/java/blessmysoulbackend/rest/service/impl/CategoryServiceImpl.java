package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.CategoryDao;
import blessmysoulbackend.rest.dto.CategoryDto;
import blessmysoulbackend.rest.model.Category;
import blessmysoulbackend.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Transactional
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @CachePut(value="categories")
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        categoryDao.findByOrderById().iterator().forEachRemaining(categoryList::add);
        return categoryList;
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id).get();
    }

    public List<Category> findFeatured() {
        List<Category> categoryList = new ArrayList<>();
        categoryDao.findByOrderById().iterator().forEachRemaining(category -> {
            if (category.isFeaturedCategory()) {
                categoryList.add(category);
            }
        });
        return categoryList;
    }

    public Category save(CategoryDto category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setAllProducts(category.getAllProducts());
        newCategory.setFeaturedCategory(category.getIsFeaturedCategory());

        categoryDao.save(newCategory);
        return categoryDao.save(newCategory);
    }

    public Category update(long id, CategoryDto category) {
        Optional<Category> optionalCategory = categoryDao.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(category.getName());
            existingCategory.setImage(category.getImage());
            existingCategory.setSequence(category.getSequence());
            existingCategory.setAllProducts(category.getAllProducts());
            existingCategory.setFeaturedCategory(category.getIsFeaturedCategory());

            return categoryDao.save(existingCategory);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        Category category = categoryDao.findById(id).get();
        categoryDao.delete(category);
    }

}
