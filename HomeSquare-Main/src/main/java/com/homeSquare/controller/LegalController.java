package com.homeSquare.controller;

import com.homeSquare.entity.LegalProduct;
import com.homeSquare.entity.Payment;
import com.homeSquare.payload.PaymentConfirmationRequest;
import com.homeSquare.service.serviceImpl.LegalProductService;
import com.homeSquare.service.serviceImpl.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// LegalController.java
// LegalController.java
@RestController
@RequestMapping("/api/legal")
public class LegalController {

    @Autowired
    private LegalProductService legalProductService;

    @Autowired
    private PaymentService paymentService;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @GetMapping("/packages")
    public ResponseEntity<List<LegalProduct>> getAllLegalPackages() {
        List<LegalProduct> legalPackages = legalProductService.getAllLegalProducts();
        return new ResponseEntity<>(legalPackages, HttpStatus.OK);
    }

    @PostMapping("/purchase/{packageId}")
    public ResponseEntity<String> purchaseLegalPackage(@PathVariable("packageId") Long productId) {
        LegalProduct legalPackage = legalProductService.getLegalProductById(productId);

        if (legalPackage == null) {
            return new ResponseEntity<>("Legal package not found", HttpStatus.NOT_FOUND);
        }

        // Create a Stripe payment intent here and get the client secret
        String clientSecret;
        try {
            clientSecret = createPaymentIntent(legalPackage.getPrice());
        } catch (StripeException e) {
            return new ResponseEntity<>("Error creating payment intent", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Return the client secret to the client side
        return new ResponseEntity<>(clientSecret, HttpStatus.OK);
    }

    @PostMapping("/confirm-payment")
    public ResponseEntity<String> confirmPayment(@RequestBody PaymentConfirmationRequest request) {
        String paymentIntentId = request.getPaymentIntentId();
        double amount = request.getAmount();
        String currency = request.getCurrency();

        // Validate the payment using Stripe API
        boolean paymentSuccessful;
        try {
            paymentSuccessful = validatePayment(paymentIntentId, amount, currency);
        } catch (StripeException e) {
            return new ResponseEntity<>("Error validating payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (paymentSuccessful) {
            Payment payment = new Payment();
            payment.setPaymentIntentId(paymentIntentId);
            payment.setAmount(amount);
            payment.setCurrency(currency);

            paymentService.savePayment(payment);

            return new ResponseEntity<>("Payment confirmed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment failed", HttpStatus.BAD_REQUEST);
        }
    }

    private String createPaymentIntent(double amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (amount * 100)); // Amount is in cents
        params.put("currency", "usd"); // Replace with your desired currency
        params.put("payment_method_types", "card");

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return paymentIntent.getClientSecret();
    }

    private boolean validatePayment(String paymentIntentId, double amount, String currency) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

        // Check if the payment was successful
        return paymentIntent.getStatus().equals("succeeded")
                && paymentIntent.getAmountReceived() == (int) (amount * 100) // Amount is in cents
                && paymentIntent.getCurrency().equalsIgnoreCase(currency);
    }

    // Stripe API integration methods (createPaymentIntent, validatePayment) go here
}
