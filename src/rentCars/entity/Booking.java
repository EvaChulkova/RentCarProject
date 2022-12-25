package rentCars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import rentCars.entity.enums.BookingStatusEnum;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Long id;
    private Integer userId;
    private Integer carId;
    private LocalDateTime rentalStart;
    private LocalDateTime rentalFinish;
    private BookingStatusEnum status;
    private String comment;


    public Booking(Integer userId, Integer carId, LocalDateTime rentalStart, LocalDateTime rentalFinish, BookingStatusEnum status, String comment) {
        this.userId = userId;
        this.carId = carId;
        this.rentalStart = rentalStart;
        this.rentalFinish = rentalFinish;
        this.status = status;
        this.comment = comment;
    }
}
