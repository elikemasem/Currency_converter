package com.dexwin.currencyconverter.Externals;

import com.dexwin.currencyconverter.DTO.CurrencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


/**
 * This class is responsible for all api calls
 */
@Service
public class ExchangeApiCalls {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * This is the endpoint for making class to the exchange api service
     * @param url - url to of the exchange host
     * @return - returns the result and maps it into the CurrencyDTO class
     */
    public CurrencyDTO getExchangeRate(String url) {
        try {
            return restTemplate.getForObject(url, CurrencyDTO.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Client error occurred while fetching exchange rate: " + e.getMessage(), e);
        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Server error occurred while fetching exchange rate: " + e.getMessage(), e);
        } catch (RuntimeException e) {
            throw new RuntimeException("Unexpected error occurred: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("General exception occurred while fetching exchange rate: " + e.getMessage(), e);
        }
    }

}
