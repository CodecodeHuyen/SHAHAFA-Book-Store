package com.fu.bookshop.service.impl;


import com.fu.bookshop.dto.PublisherDTO;
import com.fu.bookshop.dto.PublisherResponse;
import com.fu.bookshop.entity.Publisher;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.repository.PublisherRepository;
import com.fu.bookshop.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PUBLISHER-SERVICE-IMPL")
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public List<PublisherDTO> getAll() {
        return publisherRepository.findAll().stream()
                .map(p -> new PublisherDTO(
                        p.getId(),
                        p.getName()
                ))
                .toList();
    }


    @Transactional
    @Override
    public PublisherResponse createPublisher(String name) {

        if (publisherRepository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(ErrorCode.PUBLISHER_ALREADY_EXISTS);
        }

        Publisher publisher = Publisher.builder()
                .name(name.trim())
                .build();

        publisherRepository.save(publisher);

        return new PublisherResponse(
                publisher.getId(),
                publisher.getName()
        );
    }
}
