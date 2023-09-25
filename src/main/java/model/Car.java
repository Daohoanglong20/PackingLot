package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Car {
    private String lincensePlate;
    private String carColor;
    private String carType;
    private String company;
    private int parkId;

    public Car() {
    }

    public Car(String lincensePlate, String carColor, String carType, String company, int parkId) {
        this.lincensePlate = lincensePlate;
        this.carColor = carColor;
        this.carType = carType;
        this.company = company;
        this.parkId = parkId;
    }

}
