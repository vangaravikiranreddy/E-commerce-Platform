package com.payment.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentResponse {
    private String paymentLink;
}
