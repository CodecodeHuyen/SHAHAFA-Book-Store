package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.CategoryDTO;
import com.fu.bookshop.entity.Category;
import com.fu.bookshop.repository.CategoryRepository;
import com.fu.bookshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryDTO(
                        c.getId(),
                        c.getName()
                ))
                .toList();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }
}
