package com.sbrf.reboot.bankapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BalanceService implements IBalanceService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.requestUrl.getBalance}")
    private String getBalanceRequestUrl;

    @Value("${spring.requestUrl.increaseBalance}")
    private String increaseBalanceRequestUrl;

    @Value("${spring.requestUrl.decreaseBalance}")
    private String decreaseBalanceRequestUrl;

    @Value("${spring.topic.increaseBalance}")
    private String increaseBalanceTopic;

    @Value("${spring.topic.increaseBalance}")
    private String decreaseBalanceTopic;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String VALUE_FIELD = "value";

    public BalanceService(
            final KafkaTemplate<String, Object> kafkaTemplate,
            final RestTemplate restTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.restTemplate = restTemplate;
        objectMapper = new ObjectMapper();
    }

    @Override
    public Long getBalance() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader(AUTHORIZATION_HEADER)
                .replace("Bearer ", "")
        );
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        final ResponseEntity<Long> response = restTemplate.exchange(
                getBalanceRequestUrl,
                HttpMethod.GET,
                entity,
                Long.class
        );
        return response.getBody();
    }

    @Override
    public Long increaseBalance(final Long value) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setBearerAuth(
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest()
                        .getHeader(AUTHORIZATION_HEADER)
                        .replace("Bearer ", "")
        );

        final HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        final String urlTemplate = UriComponentsBuilder.fromHttpUrl(decreaseBalanceRequestUrl)
                .queryParam("value", "{value}")
                .encode()
                .toUriString();
        final Map<String, Long> params = new HashMap<>() {{
            put("value", value);
        }};
        final ResponseEntity<Long> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.PUT,
                entity,
                Long.class,
                params
        );
        return response.getBody();
    }

    @Override
    public Long decreaseBalance(final Long value) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setBearerAuth(
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest()
                        .getHeader(AUTHORIZATION_HEADER)
                        .replace("Bearer ", "")
        );

        final HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        final String urlTemplate = UriComponentsBuilder.fromHttpUrl(decreaseBalanceRequestUrl)
                .queryParam("value", "{value}")
                .encode()
                .toUriString();
        final Map<String, Long> params = new HashMap<>() {{
            put("value", value);
        }};
        final ResponseEntity<Long> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.PUT,
                entity,
                Long.class,
                params
        );
        return response.getBody();
    }
}
