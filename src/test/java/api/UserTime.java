package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTime {
    private String name;
    private String job;

    @JsonCreator
    public UserTime(@JsonProperty("name") String name,
                    @JsonProperty("job") String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

}
