package com.fu.bookshop.controller;

import com.fu.bookshop.dto.ApiResponse;
import com.fu.bookshop.dto.DeleteFileRequest;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.service.impl.S3ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


// class test api qua postman
@RestController
@RequestMapping("/api/test/s3")
@RequiredArgsConstructor
public class S3TestController {

    private final S3ServiceImpl s3Service;

    /**
     * ======================
     * TEST UPLOAD FILE
     * ======================
     * POST /api/test/s3/upload
     * form-data:
     *  - file: (choose file)
     */
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<List<String>>> uploadMultiple(
            @RequestParam("files") List<MultipartFile> files
    ) throws Exception {

        if (files == null || files.isEmpty()) {
            throw new BusinessException(ErrorCode.FILE_EMPTY);
        }

        if (files.size() > 1000) {
            throw new BusinessException(ErrorCode.FILE_LIMIT_EXCEEDED);
        }

        List<String> urls = s3Service.uploadFiles(files);

        return ResponseEntity.ok(
                ApiResponse.<List<String>>builder()
                        .success(true)
                        .code(200)
                        .message("Upload thành công " + urls.size() + " ảnh")
                        .data(urls)
                        .build()
        );
    }


    /**
     * ======================
     * TEST DELETE FILE
     * ======================
     * DELETE /api/test/s3/delete
     * body (raw JSON):
     * {
     *   "fileUrl": "https://xxx.s3.ap-southeast-1.amazonaws.com/..."
     * }
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> delete(@RequestParam("fileUrl") String fileUrl) {

        s3Service.delete(fileUrl);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .code(200)
                        .message("Xóa file thành công")
                        .build()
        );
    }
}
