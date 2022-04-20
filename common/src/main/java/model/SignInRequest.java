package model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class SignInRequest {

    @NotBlank
    @JsonProperty("username")
    @Min(value = 4, message = "should be grater than 4")
    @Max(value = 256, message = "should be less than 256")
    @Getter
    @Setter
    private String username;

    @NotBlank
    @JsonProperty("password")
    @Min(value = 4, message = "should be grater than 4")
    @Max(value = 256, message = "should be less than 256")
    @Getter
    @Setter
    private String password;

    @JsonCreator
    public SignInRequest(
            @JsonProperty("username") final String username,
            @JsonProperty("password") final String password
    ) {
        this.username = username;
        this.password = password;
    }

    @JsonCreator
    public SignInRequest() {
    }
}