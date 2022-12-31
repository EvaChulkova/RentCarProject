package rentCars.service;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DownloadClientService {
    private static final DownloadClientService INSTANCE = new DownloadClientService();
    BookingService bookingService = BookingService.getInstance();



    public static DownloadClientService getInstance() {
        return INSTANCE;
    }
}
