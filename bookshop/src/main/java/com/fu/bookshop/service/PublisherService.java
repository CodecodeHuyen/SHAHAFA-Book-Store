package com.fu.bookshop.service;

import com.fu.bookshop.dto.PublisherDTO;
import com.fu.bookshop.entity.Category;

import java.util.List;

public interface PublisherService {
    public List<PublisherDTO> getAll();
}
