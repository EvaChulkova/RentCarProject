package rentCars.mapper;

import lombok.NoArgsConstructor;
import rentCars.dto.CreateDto.CreateBookingDto;
import rentCars.entity.Booking;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateBookingMapper implements Mapper<CreateBookingDto, Booking>{
    private static final CreateBookingMapper INSTANCE = new CreateBookingMapper();

    @Override
    public Booking mapFrom(CreateBookingDto createBookingDto) {
        return new Booking(
                createBookingDto.getUserId(),
                createBookingDto.getCarId(),
                LocalDateTime.parse(createBookingDto.getRentalStart()),
                LocalDateTime.parse(createBookingDto.getRentalFinish()),
                createBookingDto.getStatus(),
                createBookingDto.getComment()
        );
    }

    public static CreateBookingMapper getInstance() {
        return INSTANCE;
    }
}
