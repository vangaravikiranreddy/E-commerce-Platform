package com.payment.paymentservice.paymentgateway;

import com.payment.paymentservice.dtos.InitiatePaymentResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) throws StripeException, RazorpayException;
}
