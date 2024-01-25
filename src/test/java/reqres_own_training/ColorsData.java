package reqres_own_training;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ColorsData {
   private Integer id;
    private String name;
    private int year;
    private String color;
    private String pantone_value;

    @JsonCreator
    public ColorsData(@JsonProperty("id") Integer id,
                      @JsonProperty("name") String name,
                      @JsonProperty("year") int year,
                      @JsonProperty("color") String color,
                      @JsonProperty("pantone_value") String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getPantone_value() {
        return pantone_value;
    }
}
