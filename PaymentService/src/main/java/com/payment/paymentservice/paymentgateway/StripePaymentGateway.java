package com.payment.paymentservice.paymentgateway;

import com.payment.paymentservice.dtos.InitiatePaymentResponse;
import com.stripe.model.PaymentLink;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Component
public class StripePaymentGateway implements PaymentGateway{
    @Value("${stripe.secret.key}")
    private String stripeKey;
    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) throws StripeException {
        Stripe.apiKey = stripeKey;

        //List<map> lineItems;
//        PaymentLinkCreateParams params =
//                PaymentLinkCreateParams.builder()
//                        .addLineItem(
//                                PaymentLinkCreateParams.LineItem.builder()
//                                        .setPrice("price_1MoC3TLkdIwHu7ixcIbKelAC")
//                                        .setQuantity(1L)
//                                        .build()
//                        )
//                        .build();

        Map<String, Object> priceData = new HashMap<>();
        priceData.put("unit_amount", amount);
        priceData.put("currency", "INR");

        Map<String, Object> productData = new HashMap<>();
        productData.put("name", "iPhone");
        //productData.put("quantity", 2);
        priceData.put("product_data", productData);

        Price price = Price.create(priceData);

        //params.put("line_items", );
        Map<String, Object> lineItem1 = new HashMap<>();
        lineItem1.put("price", price.getId());
        lineItem1.put("quantity", 5);

        Map<String, Object> afterPayment = new HashMap<>();
        afterPayment.put("type", "redirect");

        Map<String, Object> redirect = new HashMap<>(); //lineItem2
        redirect.put("url", "https://scaler.com/");

        afterPayment.put("redirect", redirect);

        List<Object> lineItems = new ArrayList<>();
        lineItems.add(lineItem1);

        Map<String, Object> params = new HashMap<>();
        params.put("line_items", lineItems);
        params.put("after_completion", afterPayment);
        //params.put("generate_invoice", map);

        PaymentLink paymentLink = com.stripe.model.PaymentLink.create(params);

        return paymentLink.getUrl();
    }
}
