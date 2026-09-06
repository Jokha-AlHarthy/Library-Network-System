package com.mini.project.services;

import com.mini.project.entities.Branch;
import com.mini.project.entities.Category;
import com.mini.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }




    //Add service
    public Long addCategory(String name, String description){
        Category category =  new Category();
        category.setIsActive(true);
        category.setCreatedDate(new Date());
        category.setName(name);
        category.setDescription(description);
        category = categoryRepository.save(category);
        return category.getId();
    }

    //Get All categories service
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    //Get categories By Id service
    public Category getById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent() && category.get().getIsActive()) {
            return category.get();
        }
        return new Category();
    }

    //Update service
    public Category updateCategory(Long id, String updateName, String updateDescription) throws Exception{
        Category categoryToUpdate =  categoryRepository.getById(id);
        if(categoryToUpdate==null){
            throw new Exception("Category is not found by the id");
        }
        categoryToUpdate.setUpdatedDate(new Date());
        categoryToUpdate.setName(updateName);
        categoryToUpdate.setName(updateName);
        categoryToUpdate.setDescription(updateDescription);
        categoryToUpdate = categoryRepository.save(categoryToUpdate);
        return categoryToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Category deleteCategory = categoryRepository.getById(id);
        if(deleteCategory == null){
            return false;
        }
        deleteCategory.setIsActive(false);
        deleteCategory.setUpdatedDate(new Date());
        categoryRepository.save(deleteCategory);
        return true;
    }
}
