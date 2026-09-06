package com.mini.project.controllers;

import com.mini.project.entities.Category;
import com.mini.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("add")
    public Long addCategory(@RequestParam String name,
                            @RequestParam String description) {
        return categoryService.addCategory(name, description);
    }

    @GetMapping("getAll")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("getById")
    public Category getById(@RequestParam Long id) {
        return categoryService.getById(id);
    }

    @PutMapping("update")
    public Category updateCategory(@RequestParam Long id,
                                   @RequestParam String updateName,
                                   @RequestParam String updateDescription) throws Exception {
        return categoryService.updateCategory(id, updateName, updateDescription);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteCategory(@RequestParam Long id) {
        return categoryService.deleteById(id);
    }
}
