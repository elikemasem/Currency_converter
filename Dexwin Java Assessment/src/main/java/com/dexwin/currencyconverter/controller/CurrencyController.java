package com.dexwin.currencyconverter.controller;

import com.dexwin.currencyconverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @Value("${dexwin.properties.currency}")
    private String defaultCurrency; //this is the api key value stores in the yaml


    public CurrencyController(final CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * This is the endpoint that converts does the currency conversion based on the users preference
     * Because euros is the default currency used in Dexwin, the source is set of euros if null or empty
     * and returns the exchange rate amount
     * @param source - the currency to be converted
     * @param target - the currency seeking to know it value based on the sources
     * @param amount - the amount on the source currency in question
     * @return the amount in double
     */
    @GetMapping(value = "currencies/convert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> convert(
            @RequestParam(value = "source", defaultValue = "EUR") String source,
            @RequestParam("target") String target,
            @RequestParam("amount") double amount) {
        try {
            // Validate target currency and amount
            if (target == null || target.isEmpty()) {
                return ResponseEntity.badRequest().body("Target currency must be provided.");
            }
            if (amount <= 0) {
                return ResponseEntity.badRequest().body("Amount must be greater than zero.");
            }

            double convertedAmount = currencyService.convert(source.toUpperCase(), target.toUpperCase(), amount);
            return ResponseEntity.ok(convertedAmount);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid currency or amount format.");

        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Client error: " + e.getMessage());

        } catch (HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Server error: " + e.getMessage());

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }


}
