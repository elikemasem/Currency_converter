package com.dexwin.currencyconverter.service;

import com.dexwin.currencyconverter.DTO.CurrencyDTO;
import com.dexwin.currencyconverter.Externals.ExchangeApiCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * TODO: Implementation of this class has to be backed by https://api.exchangerate.host/latest?base=EUR&symbols=AUD,CAD,CHF,CNY,GBP,JPY,USD
 */
@Service
public class CurrencyExchangeRateService implements CurrencyService {

    @Autowired
    private ExchangeApiCalls exchangeApiCalls;

    @Value("${exchangerate.properties.url}")
    private String exchangeUrl;

    @Value("${exchangerate.properties.apiKey}")
    private String getExhangeUrl;

    /**
     * This is the method that handles the business logic for the conversion
     * @param source - the currency you have
     * @param target - the currency you want to find its value
     * @param amount - the amount you want to check
     * @return the value of the cash in double
     */
    @Override
    public double convert(String source, String target, double amount) {
        String url = String.format("%s?access_key=%s&from=%s&to=%s&amount=%.2f",
                exchangeUrl, getExhangeUrl, source, target, amount);
        System.out.println("This is the Url: "+url);

        CurrencyDTO currencyDTO = exchangeApiCalls.getExchangeRate(url);
        if (currencyDTO == null) {
            throw new RuntimeException("Exchange rate not found: " + url);
        }

        if (currencyDTO.isSuccess() == true){
            double result = currencyDTO.getResult();
            System.out.println("Exchange rate: "+result);
            return result;
        }else {
            throw new RuntimeException("Unable to generate exchange rate: " + url);
        }


    }
}
