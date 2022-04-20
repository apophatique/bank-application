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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@Service
public class BalanceService implements IBalanceService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final ObjectMapper objectMapper;

    @Value("${spring.requestUrl.getBalance}")
    private String getBalanceRequestUrl;

    @Value("${spring.topic.increaseBalance}")
    private String increaseBalanceTopic;

    @Value("${spring.topic.increaseBalance}")
    private String decreaseBalanceTopic;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public BalanceService(
            final KafkaTemplate<String, Object> kafkaTemplate,
            final RestTemplate restTemplate,
            final HttpHeaders httpHeaders
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        objectMapper = new ObjectMapper();
    }

    @Override
    public Long getBalance(final Integer userId) {
        httpHeaders.setBearerAuth(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization")
                .replace("Bearer ", "")
        );
        final HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        final ResponseEntity<Long> response = restTemplate.exchange(
                String.format(getBalanceRequestUrl + "/%d", userId),
                HttpMethod.GET,
                entity,
                Long.class
        );
        return response.getBody();
    }

    @Override
    public Long increaseBalance(final Integer id, final Long value) {
        final String msg = String.format(
                   // "{\"id\": \"%d\", \"valueForUpdated\": \"%d\"}",
                "{id: %d, valueForUpdated: %d}",
                id,
                value
        );
        final ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(
                increaseBalanceTopic, msg
        );
        kafkaTemplate.send(increaseBalanceTopic, id);
        kafkaTemplate.send(increaseBalanceTopic, value);
        kafkaTemplate.flush();
        return null;
    }

    @Override
    public Long decreaseBalance(final Integer id, final Long value) {
        final String msg = String.format(
                "{\"id\": %d, \"valueForUpdated\": \"%d\"}",
                id,
                value
        );
        final ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(
                decreaseBalanceTopic, msg
        );

        kafkaTemplate.send(producerRecord);

        return null;
    }
}
