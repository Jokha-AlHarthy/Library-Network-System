package com.mini.project.controllers;

import com.mini.project.dto.CategoryDTO;
import com.mini.project.entities.Category;
import com.mini.project.services.CategoryService;
import jakarta.validation.Valid;
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
    public Long addCategory(@Valid @RequestBody CategoryDTO dto) {
        return categoryService.addCategory(
                dto.getCategoryName(),
                dto.getCategoryDescription());
    }

    @GetMapping("getAll")
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = CategoryDTO.convertToDTO(categoryService.getAllCategories());
        return categories;
    }

    @GetMapping("getById")
    public CategoryDTO getById(@RequestParam Long id) {
        return CategoryDTO.convertToDTO(categoryService.getById(id));
    }

    @PutMapping("update")
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO dto) throws Exception {
        return CategoryDTO.convertToDTO(categoryService.updateCategory(
                dto.getCategoryId(),
                dto.getCategoryName(),
                dto.getCategoryDescription()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteCategory(@RequestParam Long id) {
        return categoryService.deleteById(id);
    }
}
