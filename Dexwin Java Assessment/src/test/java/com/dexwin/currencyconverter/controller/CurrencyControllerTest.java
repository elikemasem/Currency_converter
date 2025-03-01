package com.dexwin.currencyconverter.controller;

import com.dexwin.currencyconverter.service.CurrencyService;
import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static java.lang.Double.parseDouble;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    /*    @Test
        public void should_convert_EUR_to_USD_with_rate_greater_than_1() throws Exception {
            this.mockMvc.perform(get("/currencies/convert?source=EUR&target=USD&amount=1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            new AssertionMatcher<>() {
                                @Override
                                public void assertion(String value) throws AssertionError {
                                    assertThat(parseDouble(value)).isGreaterThan(1.0);
                                }
                            })
                    );
        }

        @Test
        public void should_convert_USD_to_EUR_with_rate_less_than_1() throws Exception {
            this.mockMvc.perform(get("/currencies/convert?source=USD&target=EUR&amount=1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            new AssertionMatcher<>() {
                                @Override
                                public void assertion(String value) throws AssertionError {
                                    assertThat(parseDouble(value)).isLessThan(1.0);
                                }
                            })
                    );
        }*/
    @Test
    public void should_convert_EUR_to_USD_with_rate_greater_than_1() throws Exception {
        double convertedAmount = 1.1; // Assume a conversion rate > 1
        Mockito.when(currencyService.convert("EUR", "USD", 1.0)).thenReturn(convertedAmount);

        MvcResult result = this.mockMvc.perform(get("/currencies/convert")
                        .param("source", "EUR")
                        .param("target", "USD")
                        .param("amount", "1"))
                .andExpect(status().isOk())
                .andReturn();

        double responseValue = Double.parseDouble(result.getResponse().getContentAsString());
        assertThat(responseValue).isGreaterThan(1.0);
    }


    @Test
    public void should_convert_USD_to_EUR_with_rate_less_than_1() throws Exception {
        double convertedAmount = 0.9; // Assume a conversion rate < 1
        Mockito.when(currencyService.convert("USD", "EUR", 1.0)).thenReturn(convertedAmount);

        MvcResult result = this.mockMvc.perform(get("/currencies/convert")
                        .param("source", "USD")
                        .param("target", "EUR")
                        .param("amount", "1"))
                .andExpect(status().isOk())
                .andReturn();

        double responseValue = Double.parseDouble(result.getResponse().getContentAsString());
        assertThat(responseValue).isLessThan(1.0);
    }
}