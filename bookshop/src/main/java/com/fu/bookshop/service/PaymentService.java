package com.fu.bookshop.service;

import vn.payos.model.webhooks.Webhook;
import vn.payos.model.webhooks.WebhookData;

public interface PaymentService {
    String createPaymentLink(long or, int amount, String productName) throws Exception;

    WebhookData verifyWebhook(Webhook webhookBody) throws Exception;
}
