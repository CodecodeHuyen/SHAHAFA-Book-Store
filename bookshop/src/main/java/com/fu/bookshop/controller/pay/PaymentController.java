package com.fu.bookshop.controller.pay;

import com.fu.bookshop.dto.PaymentRequestDto;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.service.OrderService;
import com.fu.bookshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.payos.model.webhooks.Webhook;
import vn.payos.model.webhooks.WebhookData;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;

    @Autowired
    public PaymentController(PaymentService paymentService, OrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @PostMapping("/create-link")
    public String createPaymentLink(@RequestBody PaymentRequestDto dto) {
        System.out.println(">>> /api/v1/payment/create-link CALLED, dto = " + dto);
        try {
            long orderId = System.currentTimeMillis();
            return paymentService.createPaymentLink(orderId, dto.getAmount(),
                    dto.getDescription() != null ? dto.getDescription() : "Đơn hàng Bookshop");
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi tạo link: " + e.getMessage();
        }
    }

    @PostMapping("/payos-hook")
    public ResponseEntity<String> handlePayOSWebhook(@RequestBody Webhook webhook) {
        System.out.println(">>> WEBHOOK BODY = " + webhook);
        try {
            WebhookData data = paymentService.verifyWebhook(webhook);

            System.out.println("Thanh toán thành công đơn hàng: " + data.getOrderCode());
            Order order = orderService.getOrderByCode(String.valueOf(data.getOrderCode()));
            if (order != null) {
                orderService.updateOrderStatus(order, "COMPLETE");
                System.out.println("Cập nhật trạng thái đơn hàng thành công: " + order.getCode());
            } else {
                System.out.println("Không tìm thấy đơn hàng với mã: " + data.getOrderCode());
            }

            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid webhook: " + e.getMessage());
        }
    }

}
