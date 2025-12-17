package com.fu.bookshop.service.impl;

import com.fu.bookshop.service.PaymentService;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkRequest;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkResponse;
import vn.payos.model.webhooks.Webhook;
import vn.payos.model.webhooks.WebhookData;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PayOS payOS;

    public PaymentServiceImpl(PayOS payOS) {
        this.payOS = payOS;
    }

    @Override
    public String createPaymentLink(long orderId, int amount, String productName) throws Exception {
        System.out.println(">>> createPaymentLink() CALLED, orderCode=" + orderId + ", amount=" + amount);

        // 1. Lấy mô tả gốc (từ controller truyền xuống)
        String desc = (productName != null && !productName.isBlank())
                ? productName.trim()          // ví dụ: "Bookshop #123"
                : ("Order " + orderId);       // fallback: "Order 123456"

        // 2. Đảm bảo không quá 25 ký tự (rule PayOS)
        if (desc.length() > 25) {
            desc = desc.substring(0, 25);
        }

        System.out.println(">>> FINAL description send to PayOS = [" + desc + "], len=" + desc.length());

        // 3. Tạo request PayOS
        CreatePaymentLinkRequest paymentRequest = CreatePaymentLinkRequest.builder()
                .orderCode(orderId)
                .amount((long) amount)
                .description(desc)
                .returnUrl("http://localhost:8080/success")
                .cancelUrl("http://localhost:8080/cancel")
                .build();

        try {
            CreatePaymentLinkResponse response = payOS
                    .paymentRequests()
                    .create(paymentRequest);

            System.out.println(">>> PayOS checkoutUrl = " + response.getCheckoutUrl());
            return response.getCheckoutUrl();
        } catch (Exception e) {
            System.out.println(">>> ERROR in createPaymentLink: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public WebhookData verifyWebhook(Webhook webhookBody) throws Exception {
        System.out.println(">>> VERIFY WEBHOOK, signature = " + webhookBody.getSignature());
        WebhookData data = payOS.webhooks().verify(webhookBody);
        System.out.println(">>> VERIFY DONE, orderCode = " + data.getOrderCode());
        return data;
    }
}
