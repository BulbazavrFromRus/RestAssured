package reqres_own_training;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistrationData {
    private String email;
    private String password;

    @JsonCreator
    public UserRegistrationData(@JsonProperty("email") String email,
                                @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
