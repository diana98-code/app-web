package com.distribuida.Client;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Produces;
import org.springframework.web.client.RestTemplate;

@ApplicationScoped
public class ClientProducer {

    @Produces
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
