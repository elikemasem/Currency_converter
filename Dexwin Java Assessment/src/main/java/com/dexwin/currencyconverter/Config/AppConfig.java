package com.dexwin.currencyconverter.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


    /**
     * Creates and configures a {@link RestTemplate} bean.
     * This bean is used for making RESTful API calls within the application.
     * It can be further customized with interceptors, message converters, or error handlers if needed.
     *
     * @return A new instance of {@link RestTemplate}.
     */
    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }

}
