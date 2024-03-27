package com.payment.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequest {
    private String orderId;
    private String email;
    private Long amount;
    private String phoneNumber;
}
