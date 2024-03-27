package com.payment.paymentservice.paymentgateway;


import com.razorpay.PaymentLink;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Component
public class RazorpayPaymentGateway implements PaymentGateway{
    private RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) throws RazorpayException {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        //paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1706286644);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for Order Id: #123456");
        JSONObject customer = new JSONObject();
        customer.put("name",phoneNumber);
        customer.put("contact","Ravi Kiran");
        customer.put("email",email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}
