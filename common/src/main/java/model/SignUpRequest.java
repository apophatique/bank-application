package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * "Sign up request" model.
 *
 * It can be created from JSON object.
 */
public class SignUpRequest {
    @NotBlank
    @JsonProperty("username")
    @Setter
    @Getter
    private String username;

    @NotBlank
    @JsonProperty("password")
    @Setter
    @Getter
    private String password;

    @JsonCreator
    public SignUpRequest() {
    }

    @JsonCreator
    public SignUpRequest(
            @JsonProperty("username") final String username,
            @JsonProperty("password") final String password
    ) {
        this.username = username;
        this.password = password;
    }
}
