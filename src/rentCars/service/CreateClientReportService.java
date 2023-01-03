package rentCars.service;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateClientReportService {
    private static final CreateClientReportService INSTANCE = new CreateClientReportService();
    BookingService bookingService = BookingService.getInstance();



    public static CreateClientReportService getInstance() {
        return INSTANCE;
    }
}
