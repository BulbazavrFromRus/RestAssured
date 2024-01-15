package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTimeResponse extends UserTime{


    private String updatedAt;

    @JsonCreator
    public UserTimeResponse(@JsonProperty("updatedAt") String updatedAt,
                            @JsonProperty("name") String name,
                            @JsonProperty("job") String job){
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
