package com.fu.bookshop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ghn")
public class GhnController {

    @Value("${ghn.token}")
    private String TOKEN;

    @Value("${ghn.base-url}")
    private String BASE_URL;

    @Value("${ghn.shop-id}")
    private String SHOP_ID;

    private final RestTemplate restTemplate = new RestTemplate();

    /* ========= 1. LẤY TỈNH ========= */
    @GetMapping("/provinces")
    public ResponseEntity<?> getProvinces() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                BASE_URL + "/shiip/public-api/master-data/province",
                HttpMethod.GET,
                entity,
                Map.class
        );

        return ResponseEntity.ok(response.getBody());
    }

    /* ========= 2. LẤY HUYỆN ========= */
    @GetMapping("/districts")
    public ResponseEntity<?> getDistricts(@RequestParam int provinceId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = BASE_URL
                + "/shiip/public-api/master-data/district?province_id="
                + provinceId;

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );

        return ResponseEntity.ok(response.getBody());
    }

    /* ========= 3. LẤY XÃ ========= */
    @GetMapping("/wards")
    public ResponseEntity<?> getWards(@RequestParam("districtId") Integer districtId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // GHN cần district_id trong body → ta vẫn POST sang GHN
        Map<String, Integer> body = new HashMap<>();
        body.put("district_id", districtId);

        HttpEntity<Map<String, Integer>> entity =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                BASE_URL + "/shiip/public-api/master-data/ward",
                HttpMethod.POST,
                entity,
                Map.class
        );

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/fee")
    public ResponseEntity<?> calculateFee(
            @RequestParam("districtId") Integer districtId,
            @RequestParam("wardCode") String wardCode,
            @RequestParam("weight") Integer weight) {

        // 1. Tạo headers cho yêu cầu API của GHN
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Token", TOKEN);
        headers.set("ShopId", SHOP_ID);

        // 2. Tạo body cho yêu cầu gửi sang GHN
        // (Lưu ý: GHN vẫn yêu cầu POST nên ta vẫn giữ logic Map và POST sang phía họ)
        Map<String, Object> body = new HashMap<>();
        body.put("service_type_id", 2);
        body.put("to_district_id", districtId);
        body.put("to_ward_code", wardCode);
        body.put("weight", weight);
        body.put("insurance_value", 0);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // 3. Gọi API GHN
        ResponseEntity<Map> response = restTemplate.exchange(
                BASE_URL + "/shiip/public-api/v2/shipping-order/fee",
                HttpMethod.POST,
                entity,
                Map.class
        );

        return ResponseEntity.ok(response.getBody());
    }

}

