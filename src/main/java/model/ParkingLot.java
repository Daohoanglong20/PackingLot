package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ParkingLot {
    private int parkId;
    private int parkArea;
    private String parkName;
    private String parkPlace;
    private double parkPrice;
    private String parkStatus;

    public ParkingLot() {
    }

    public ParkingLot(int parkId, int parkArea, String parkName, String parkPlace, double parkPrice, String parkStatus) {
        this.parkId = parkId;
        this.parkArea = parkArea;
        this.parkName = parkName;
        this.parkPlace = parkPlace;
        this.parkPrice = parkPrice;
        this.parkStatus = parkStatus;
    }

    public ParkingLot(String parkName, String parkPlace, int parkArea, double parkPrice, String parkStatus) {
        this.parkArea = parkArea;
        this.parkName = parkName;
        this.parkPlace = parkPlace;
        this.parkPrice = parkPrice;
        this.parkStatus = parkStatus;
    }

    public ParkingLot(int parkId, String parkName, String parkPlace, int parkArea, double parkPrice, String parkStatus) {
        this.parkId = parkId;
        this.parkArea = parkArea;
        this.parkName = parkName;
        this.parkPlace = parkPlace;
        this.parkPrice = parkPrice;
        this.parkStatus = parkStatus;
    }

}
