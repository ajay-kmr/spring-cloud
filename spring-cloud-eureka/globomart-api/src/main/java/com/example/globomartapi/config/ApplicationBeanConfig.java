package com.example.globomartapi.config;

import com.example.globomartapi.client.FeignPocApiClient;
import com.example.globomartapi.client.ProductCatalogClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Import(FeignClientsConfiguration.class)
public class ApplicationBeanConfig {

    @Value("${product.catalog.base.url}")
    String productCatalogUrl;

    @Bean
    @Autowired
    ProductCatalogClient productCatalogClient(Decoder decoder, Encoder encoder,
                                              Client client, Contract contract) {


        return Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
//                .requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
                .target(ProductCatalogClient.class, productCatalogUrl);
    }

    @Bean
    @Autowired
    FeignPocApiClient feignPocApiClient(Decoder decoder, Encoder encoder,
                                        Client client, Contract contract) {
        System.out.println("?????????????" + productCatalogUrl);
        return Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
//                .requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
                .target(FeignPocApiClient.class, productCatalogUrl);
    }
}
