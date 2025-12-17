package com.fu.bookshop.controller;

import com.fu.bookshop.dto.ApiResponse;
import com.fu.bookshop.dto.PublisherCreateRequest;
import com.fu.bookshop.dto.PublisherResponse;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createPublisher(
            @Valid @RequestBody PublisherCreateRequest request,
            BindingResult bindingResult
    ) {

        // 1️⃣ Validation lỗi (@NotBlank, @Size…)
        if (bindingResult.hasErrors()) {
            String msg = bindingResult
                    .getFieldErrors()
                    .get(0)
                    .getDefaultMessage();

            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .success(false)
                            .code(400)
                            .message(msg)
                            .build()
            );
        }

        try {
            // 2️⃣ Business
            PublisherResponse data =
                    publisherService.createPublisher(request.getName());

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .success(true)
                            .code(0)
                            .message("Tạo publisher thành công")
                            .data(data)
                            .build()
            );

        } catch (BusinessException ex) {

            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .success(false)
                            .code(ex.getErrorCode().getCode())
                            .message(ex.getMessage())
                            .build()
            );
        }
    }
}
