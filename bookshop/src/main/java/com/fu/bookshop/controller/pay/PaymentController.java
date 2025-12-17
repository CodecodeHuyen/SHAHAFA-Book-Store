package com.fu.bookshop.controller.pay;

import com.fu.bookshop.dto.PaymentRequestDto;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.service.OrderService;
import com.fu.bookshop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.payos.model.webhooks.Webhook;
import vn.payos.model.webhooks.WebhookData;

@Controller
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;

    @PostMapping("/create-link")
    public String createPaymentLink(@RequestBody PaymentRequestDto dto) {
        try {
            Order order = orderService.getOrderByCode(dto.getOrderCode());
            if (order == null) {
                throw new BusinessException(ErrorCode.ORDER_NOT_FOUND);
            }

            int amount = order.getTotalPrice().add(order.getShippingFee()).intValue();
            String description = "Thanh toan don hang " + order.getCode();
            String paymentUrl = paymentService.createPaymentLink(order.getId(), amount, description);

            return "redirect:" + paymentUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
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
