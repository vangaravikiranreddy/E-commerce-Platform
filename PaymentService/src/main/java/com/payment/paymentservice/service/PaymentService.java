package com.payment.paymentservice.service;

import com.payment.paymentservice.dtos.InitiatePaymentResponse;
import com.payment.paymentservice.paymentgateway.PaymentGateway;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(String orderId, String email, String phoneNumber, Long amount) throws StripeException, RazorpayException {
        String paymentLink = paymentGateway.generatePaymentLink(orderId, email, phoneNumber, amount);

//        InitiatePaymentResponse initiatePaymentResponse = new InitiatePaymentResponse();
//        initiatePaymentResponse.setPaymentLink(paymentLink);
//        return initiatePaymentResponse;
        return paymentLink;
    }
}
