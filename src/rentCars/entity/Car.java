package rentCars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import rentCars.entity.enums.CarColorEnum;
import rentCars.entity.enums.CarStatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class Car {
    private Integer id;
    private String brand;
    private CarColorEnum color;
    private Integer seatAmount;
    private Integer price;
    private CarStatusEnum status;
    private String image;


    public Car(String brand, CarColorEnum color, Integer seatAmount, Integer price, CarStatusEnum status, String image) {
        this.brand = brand;
        this.color = color;
        this.seatAmount = seatAmount;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public Car(String brand, CarColorEnum color, Integer seatAmount, String image) {
        this.brand = brand;
        this.color = color;
        this.seatAmount = seatAmount;
        this.image = image;
    }
}
