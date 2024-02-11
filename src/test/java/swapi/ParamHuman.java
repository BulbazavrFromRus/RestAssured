package swapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParamHuman {
    private String gender;
    private String hair_color;

    @JsonCreator
    public ParamHuman(
            @JsonProperty("gender") String gender,
            @JsonProperty("hair_color") String hair_color) {
        this.gender = gender;
        this.hair_color = hair_color;
    }

    public ParamHuman() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    @Override
    public String toString() {
        return "ParamHuman{" +
                "gender='" + gender + '\'' +
                ", hair_color='" + hair_color + '\'' +
                '}';
    }
}

