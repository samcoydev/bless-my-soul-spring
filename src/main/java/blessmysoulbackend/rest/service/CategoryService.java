package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.CategoryDto;
import blessmysoulbackend.rest.model.Category;

import java.util.List;

public interface CategoryService {

    Category save(CategoryDto item);
    Category update(long id, CategoryDto item);
    void delete(long id);

    List<Category> findAll();
    List<Category> findFeatured();
    Category findById(Long id);

}
