package rentCars.service;

import lombok.SneakyThrows;
import rentCars.dao.CarDao;
import rentCars.dto.CarDto;
import rentCars.dto.CreateDto.CreateCarDto;
import rentCars.mapper.CreateCarMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CarService {
    private static final CarService INSTANCE = new CarService();
    private final CarDao carDao = CarDao.getInstance();
    private final CreateCarMapper createCarMapper = CreateCarMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private CarService(){}

    public List<CarDto> findAll() {
        return carDao.findAll().stream()
                .map(car -> CarDto.builder()
                        .id(car.getId())
                        .brand(car.getBrand())
                        .color(car.getColor())
                        .seatAmount(car.getSeatAmount())
                        .price(car.getPrice())
                        .status(car.getStatus())
                        .image(car.getImage())
                        .build()
                )
                .collect(toList());
    }

    public List<CarDto> findAvailableCars() {
        return carDao.findAvailableCars().stream()
                .map(car -> CarDto.builder()
                        .id(car.getId())
                        .brand(car.getBrand())
                        .color(car.getColor())
                        .seatAmount(car.getSeatAmount())
                        .price(car.getPrice())
                        .status(car.getStatus())
                        .image(car.getImage())
                        .build())
                .collect(toList());
    }

    public List<CarDto> findAvailableCarById(Integer carId) {
        return carDao.findAvailableCarById(carId).stream()
                .map(carDto -> CarDto.builder()
                        .id(carDto.getId())
                        .brand(carDto.getBrand())
                        .color(carDto.getColor())
                        .seatAmount(carDto.getSeatAmount())
                        .price(carDto.getPrice())
                        .status(carDto.getStatus())
                        .image(carDto.getImage())
                        .build())
                .collect(toList());


    }

    @SneakyThrows
    public Integer create(CreateCarDto createCarDto) {
        var car = createCarMapper.mapFrom(createCarDto);
        imageService.upload(car.getImage(), createCarDto.getImage().getInputStream());
        carDao.add(car);
        return car.getId();
    }

    public static CarService getInstance(){
        return INSTANCE;
    }
}
