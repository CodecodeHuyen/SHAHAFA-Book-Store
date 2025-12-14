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
    public ResponseEntity<?> uploadMultiple(
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
                new ApiResponse(
                        "UPLOAD_SUCCESS",
                        "Upload thành công " + urls.size() + " ảnh",
                        urls
                )
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
    public ResponseEntity<?> delete(@RequestParam("fileUrl") String fileUrl) {

        s3Service.delete(fileUrl);

        return ResponseEntity.ok(
                new ApiResponse(
                        "DELETE_SUCCESS",
                        "Xóa file thành công",
                        null
                )
        );
    }
}
