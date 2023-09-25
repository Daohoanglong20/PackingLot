package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;

@Setter
@Getter
@ToString
public class Trip {
    private int tripID;
    private int bookedTicketNumber;
    private String carType;
    private Date departureDate;
    private Time departureTime;
    private String destination;
    private String driver;
    private int maximumOnlineTicketNumber;

    public Trip() {
    }

    public Trip(int tripID, int bookedTicketNumber, String carType, Date departureDate, Time departureTime,
                String destination, String driver, int maximumOnlineTicketNumber) {
        super();
        this.tripID = tripID;
        this.bookedTicketNumber = bookedTicketNumber;
        this.carType = carType;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.driver = driver;
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }
}

