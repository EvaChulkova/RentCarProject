package rentCars.dto;

import lombok.Builder;
import lombok.Value;
import rentCars.entity.enums.BookingStatusEnum;

import java.time.LocalDateTime;

@Value
@Builder
public class BookingDto {
    Long id;
    Integer userId;
    Integer carId;
    LocalDateTime rentalStart;
    LocalDateTime rentalFinish;
    BookingStatusEnum status;
    String comment;

}
