package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UnsuccessReg {
    private String error;

    @JsonCreator
    public UnsuccessReg(@JsonProperty("error") String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }


}
