package com.fu.bookshop.service.impl;

import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class S3ServiceImpl {


    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucket}") private String bucket;
    @Value("${aws.s3.folder}") private String folderName;
    @Value("${aws.s3.region}") private String region;

//https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store/Spring+boot+3+road+map.png
    public String uploadFile(MultipartFile file) throws IOException {
        String key = folderName + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromBytes(file.getBytes()));

        return String.format(
                "https://%s.s3.%s.amazonaws.com/%s",
                bucket,
                region,
                URLEncoder.encode(key, StandardCharsets.UTF_8)
        );
    }

    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; // bỏ file rỗng
            }

            urls.add(uploadFile(file));
        }

        return urls;
    }

    public void delete(String fileUrl) {
        if (fileUrl == null || fileUrl.isBlank()) {
            return; // không có ảnh thì thôi
        }

        try {
            // 1. Kiểm tra URL có đúng bucket không
            String expectedPrefix = String.format(
                    "https://%s.s3.%s.amazonaws.com/",
                    bucket,
                    region
            );

            if (!fileUrl.startsWith(expectedPrefix)) {
                throw new SystemException(ErrorCode.INVALID_FILE_URL);
            }

            // 2. Lấy key từ URL
            String encodedKey = fileUrl.substring(expectedPrefix.length());

            // decode '+' và %xx
            String key = java.net.URLDecoder.decode(encodedKey, java.nio.charset.StandardCharsets.UTF_8);

            // 3. Gửi request delete lên S3
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build());

        } catch (SystemException e) {
            throw e;
        } catch (Exception e) {
            throw new SystemException(ErrorCode.FILE_DELETE_FAILED);
        }
    }




}
