package dao;

import model.Trip;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface TripDAO {
    void addTrip(Trip trip) throws SQLException;

    List<Trip> getListTrip() throws SQLException;

    void updateTrip(String destination, String driver, int maximumOnlineTicketNumber, String cartype, Date departuredate, Time departuretime, int tripId) throws Exception;

    void updateBookedTicketByTripId(int tripID) throws Exception;

    Trip getTripByID(int tripID) throws SQLException;

    boolean deleteTrip(int tripID) throws Exception;

    int getCountTrip() throws Exception;

    List<Trip> getListTripPage(int pageIndex, int pageSize) throws SQLException, Exception;

    List<Trip> getTripSearch(String value, Date dateFrom, Date dateTo) throws SQLException, Exception;

    int getCountTicketByTripID(int tripID) throws Exception;

    int getCountOfficeByTripID(int tripID) throws Exception;

    int getCountTripSearch(String value, Date dateFrom, Date dateTo) throws SQLException, Exception;

    List<Trip> getListTripSearch(String value, Date dateFrom, Date dateTo, int pageIndex, int pageSize)
            throws SQLException, Exception;

    String getMinDate() throws SQLException, Exception;

    String getMaxDate() throws SQLException, Exception;

    int getCountTripSearchNoDate(String value) throws SQLException, Exception;

    List<Trip> getListTripSearchNoDate(String value, int pageIndex, int pageSize)
            throws SQLException, Exception;
}
