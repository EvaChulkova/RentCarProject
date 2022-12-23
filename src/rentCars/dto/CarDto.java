package rentCars.dto;

import lombok.Builder;
import lombok.Data;
import rentCars.entity.enums.CarColorEnum;
import rentCars.entity.enums.CarStatusEnum;

@Data
@Builder
public class CarDto {
    private final Integer id;
    private final String brand;
    private final CarColorEnum color;
    private final Integer seatAmount;
    private final Integer price;
    private final CarStatusEnum status;
    private final String image;
}
