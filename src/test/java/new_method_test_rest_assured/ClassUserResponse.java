package new_method_test_rest_assured;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClassUserResponse {
     private String name;
    private String job;
    private int id;

    private LocalDateTime createAt;
}
