package api;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Registr {
    private String email;
    private String password;

    @JsonCreator
    public Registr(String email, String password) {
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
