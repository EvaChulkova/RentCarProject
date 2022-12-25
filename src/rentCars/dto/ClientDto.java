package rentCars.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClientDto {
    private final Integer id;
    private final Integer userId;
    private final Integer age;
    private final Integer licenceNo;
    private final LocalDate validity;
}
