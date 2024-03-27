package com.payment.paymentservice.controller;

import com.payment.paymentservice.dtos.InitiatePaymentRequest;
import com.payment.paymentservice.dtos.InitiatePaymentResponse;
import com.payment.paymentservice.service.PaymentService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("")
    public String initiatePayment(@RequestBody InitiatePaymentRequest initiatePaymentRequest) throws StripeException, RazorpayException {
        return paymentService.initiatePayment(initiatePaymentRequest.getOrderId(), initiatePaymentRequest.getEmail(), initiatePaymentRequest.getPhoneNumber(), initiatePaymentRequest.getAmount());
    }
}
