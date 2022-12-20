package rentCars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    private Long id;
    private Integer userId;
    private Integer age;
    private Integer licenceNo;
    private LocalDate validity;
}
