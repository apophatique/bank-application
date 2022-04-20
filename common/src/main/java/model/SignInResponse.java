package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * "Sign in response" model.
 * <p>
 * It can be created from or converted to JSON object.
 */
public class SignInResponse {

    @JsonProperty("token")
    @NotBlank
    @Getter
    @Setter
    private String token;


    @JsonCreator
    public SignInResponse(@JsonProperty("token") final String token) {
        this.token = token;
    }


    @JsonCreator
    public SignInResponse() {
    }
}
