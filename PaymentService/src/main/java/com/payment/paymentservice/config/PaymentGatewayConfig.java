package com.payment.paymentservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class PaymentGatewayConfig {
    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String secretKey;

    @Bean
    public RazorpayClient getRazorPayClient() throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(keyId, secretKey);
        return razorpay;
    }
}
