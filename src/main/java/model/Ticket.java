package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;

@Setter
@Getter
@ToString


public class Ticket {
    private int ticketID;
    private Time bookingTime;
    private String customerName;
    private String licensePlate;
    private int tripID;
    private String tripName;

    public Ticket() {
    }

    public Ticket(int ticketID, Time bookingTime, String customerName, String licensePlate, int tripID) {
        super();
        this.ticketID = ticketID;
        this.bookingTime = bookingTime;
        this.customerName = customerName;
        this.licensePlate = licensePlate;
        this.tripID = tripID;
    }

    public Ticket(int ticketID, Time bookingTime, String customerName, String licensePlate, int tripID,
                  String tripName) {
        super();
        this.ticketID = ticketID;
        this.bookingTime = bookingTime;
        this.customerName = customerName;
        this.licensePlate = licensePlate;
        this.tripID = tripID;
        this.tripName = tripName;
    }
}

