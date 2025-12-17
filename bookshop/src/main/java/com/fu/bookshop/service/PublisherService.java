package com.fu.bookshop.service;

import com.fu.bookshop.dto.PublisherDTO;
import com.fu.bookshop.dto.PublisherResponse;
import com.fu.bookshop.entity.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PublisherService {
    public List<PublisherDTO> getAll();

    @Transactional
    PublisherResponse createPublisher(String name);
}
