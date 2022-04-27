package com.sbrf.reboot.bankapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.SignInRequest;
import model.SignUpRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Класс, осуществляющий взаимодействие стороны клиента и сервера на регистрацию и авторизацию.
 */
@Service
public class LoginServiceURLRequest implements LoginService {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final ObjectMapper objectMapper;

    @Value("${spring.requestUrl.signIn}")
    private String signInRequestUrl;
    @Value("${spring.requestUrl.signUp}")
    private String signUpRequestUrl;

    private final String signInUsernameField = "username";
    private final String signInPasswordField = "password";

    public LoginServiceURLRequest(final RestTemplate restTemplate, final HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        objectMapper = new ObjectMapper();

        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * Метод, осуществляющий запрос на регистрацию через брокер сообщений Kafka
     *
     * @param signUpRequest - {@link SignUpRequest} модель запроса на регистрацию
     */
    public String signUp(final SignUpRequest signUpRequest) {
        final var jsonObject = new JSONObject();
        jsonObject.put(signInUsernameField, signUpRequest.getUsername());
        jsonObject.put(signInPasswordField, signUpRequest.getPassword());
        final HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        return restTemplate.postForObject(
                signUpRequestUrl,
                entity,
                String.class
        );
    }

    /**
     * Метод, осуществляющий запрос на авторизацию через RestTemplate {@link RestTemplate}
     *
     * @param signInRequest - модель авторизации
     * @return - JSON-объект, содержащий в себе поле Token - jwt-токен
     * @throws JsonProcessingException - на случай ошибки парсинга джсон-строки в джсон-объект
     */
    @Override
    public JsonNode signIn(final SignInRequest signInRequest) throws JsonProcessingException {
        final var jsonObject = new JSONObject();
        jsonObject.put(signInUsernameField, signInRequest.getUsername());
        jsonObject.put(signInPasswordField, signInRequest.getPassword());
        final HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        final String response = restTemplate.postForObject(
                signInRequestUrl,
                entity,
                String.class
        );
        return objectMapper.readTree(response);
    }
}
