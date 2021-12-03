package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.CategoryDto;
import blessmysoulbackend.rest.model.Category;
import blessmysoulbackend.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/item/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        System.out.println("[GET] All Categories");
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryByID(@PathVariable Long id) {
        System.out.println("[GET] Category with ID: " + id);
        return categoryService.findById(id);
    }

    @PostMapping
    public Category saveCategory(@Valid @RequestBody CategoryDto category) {
        System.out.println("[POST] " + category.getId());
        System.out.println("[POST] " + category.toString());
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDto category) {
        System.out.println("[PUT] Category with an id of: " + category.getId());
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        System.out.println("[DELETE] Category: " + id);
        categoryService.delete(id);
    }
}
