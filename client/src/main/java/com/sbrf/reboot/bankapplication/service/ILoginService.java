package com.sbrf.reboot.bankapplication.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import model.SignInRequest;
import model.SignUpRequest;

public interface ILoginService {
    String signUp(final SignUpRequest signUpRequest) ;
    JsonNode signIn(final SignInRequest signInRequest) throws JsonProcessingException;
}
