package com.payment.paymentservice.controller;

import com.stripe.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripewebhook")
public class StripeWebhookController {
    @PostMapping("")
    public void updatePayment(@RequestBody Event event) {
        if (event.getType() == "invoice.payment_succeeded") {

        }
    }
}
