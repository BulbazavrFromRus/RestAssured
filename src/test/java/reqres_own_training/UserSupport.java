package reqres_own_training;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSupport {
    private String url;
    private String text;

    @JsonCreator
    public UserSupport(@JsonProperty("url") String url,
                       @JsonProperty("text") String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
