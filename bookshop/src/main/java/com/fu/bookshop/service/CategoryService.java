package com.fu.bookshop.service;

import com.fu.bookshop.dto.CategoryDTO;
import com.fu.bookshop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    public List<CategoryDTO> getAll();
}
