package com.homeSquare.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.secret.key}")
    private String secretKey;

    // This method will be automatically called during application startup
    public StripeConfig(@Value("${stripe.secret.key}") String secretKey) {
        Stripe.apiKey = secretKey;
    }
}


