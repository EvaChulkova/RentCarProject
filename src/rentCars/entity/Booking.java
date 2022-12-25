package rentCars.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rentCars.entity.enums.BookingStatusEnum;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@EqualsAndHashCode
public class Booking {
    private Long id;
    private Integer userId;
    private Integer carId;
    private LocalDateTime rentalStart;
    private LocalDateTime rentalFinish;
    private BookingStatusEnum status;
    private String comment;

    public Booking(Long id, Integer userId, Integer carId, LocalDateTime rentalStart, LocalDateTime rentalFinish, BookingStatusEnum status, String comment) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.rentalStart = rentalStart;
        this.rentalFinish = rentalFinish;
        this.status = status;
        this.comment = comment;
    }

    public Booking(Integer userId, Integer carId, LocalDateTime rentalStart, LocalDateTime rentalFinish, BookingStatusEnum status, String comment) {
        this.userId = userId;
        this.carId = carId;
        this.rentalStart = rentalStart;
        this.rentalFinish = rentalFinish;
        this.status = status;
        this.comment = comment;
    }


    public Booking(){}

    @Override
    public String toString() {
        return "Booking{" +
               "id=" + id +
               ", userId=" + userId +
               ", carId=" + carId +
               ", rentalStart=" + rentalStart +
               ", rentalFinish=" + rentalFinish +
               ", status=" + status +
               ", comment='" + comment + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public LocalDateTime getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(LocalDateTime rentalStart) {
        this.rentalStart = rentalStart;
    }

    public LocalDateTime getRentalFinish() {
        return rentalFinish;
    }

    public void setRentalFinish(LocalDateTime rentalFinish) {
        this.rentalFinish = rentalFinish;
    }

    public BookingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BookingStatusEnum status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
