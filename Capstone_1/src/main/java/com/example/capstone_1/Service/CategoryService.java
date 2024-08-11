package com.example.capstone_1.Service;

import com.example.capstone_1.Model.Category;
import com.example.capstone_1.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ArrayList<Category> getCategories() {
        return new ArrayList<Category>(categoryRepository.findAll());
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public boolean updateCategory(int id,Category category) {
        Category c = categoryRepository.getById(id);
        if (c != null) {
            c.setName(category.getName());
            categoryRepository.save(c);
            return true;
        }
        return false;
    }

    public boolean deleteCategory(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(int id) {
        if (categoryRepository.existsById(id)) {
            return true;
        }
        return false;
    }
}
