package dao.Impl;

import common.SQLConnection;
import dao.TripDAO;
import model.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDAOImpl implements TripDAO {
    SQLConnection SQLConnection = new SQLConnection();
    private Connection Connection = null;
    private PreparedStatement PreparedStatement = null;
    private ResultSet ResultSet = null;

    @Override
    public void addTrip(Trip trip) throws SQLException {
        try {
            String sql = "insert into trip(bookedTicketNumber,carType,departureDate,departureTime,destination,driver,maximumOnlineTicketNumber) Values (?,?,?,?,?,?,?)";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            PreparedStatement.setInt(1, trip.getBookedTicketNumber());
            PreparedStatement.setString(2, trip.getCarType());
            PreparedStatement.setDate(3, trip.getDepartureDate());
            PreparedStatement.setTime(4, trip.getDepartureTime());
            PreparedStatement.setString(5, trip.getDestination());
            PreparedStatement.setString(6, trip.getDriver());
            PreparedStatement.setInt(7, trip.getMaximumOnlineTicketNumber());
            PreparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }

    @Override
    public List<Trip> getListTrip() throws SQLException {
        List<Trip> list = new ArrayList<Trip>();
        try {
            String sql = "SELECT * FROM [CarPark].[dbo].[trip]";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                int tripID = ResultSet.getInt("tripID");
                int bookedTicketNumber = ResultSet.getInt("bookedTicketNumber");
                String carType = ResultSet.getString("carType");
                Date departureDate = ResultSet.getDate("departureDate");
                Time departureTime = ResultSet.getTime("departureTime");
                String destination = ResultSet.getString("destination");
                String driver = ResultSet.getString("driver");
                int maximumOnlineTicketNumber = ResultSet.getInt("maximumOnlineTicketNumber");
                list.add(new Trip(tripID, bookedTicketNumber, carType, departureDate, departureTime, destination,
                        driver, maximumOnlineTicketNumber));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return list;
    }

    @Override
    public void updateTrip(String destination, String driver, int maximumOnlineTicketNumber, String cartype, Date departuredate, Time departuretime, int tripId) throws Exception {
        String sql = "update Trip set carType = ?, departureDate = ?, departureTime = ?, destination = ?, driver = ?, maximumOnlineTicketNumber = ? where tripID = ?";
        try (Connection Connection = SQLConnection.getConnection();
        PreparedStatement PreparedStatement = Connection.prepareStatement(sql)){

            PreparedStatement.setString(1, cartype);
            PreparedStatement.setDate(2, departuredate);
            PreparedStatement.setTime(3, departuretime);
            PreparedStatement.setString(4, destination);
            PreparedStatement.setString(5, driver);
            PreparedStatement.setInt(6, maximumOnlineTicketNumber);
            PreparedStatement.setInt(7, tripId);
            PreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }

    }

    @Override
    public void updateBookedTicketByTripId(int tripID) throws Exception {
        try {
            String sql = "UPDATE trip SET bookedTicketNumber = bookedTicketNumber + 1 WHERE tripId = ?";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);

            PreparedStatement.setInt(1, tripID);

            PreparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }

    @Override
    public Trip getTripByID(int tripID) throws SQLException {
        Trip trip = new Trip();
        try {
            String sql = "select * from trip where tripID = ?";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            PreparedStatement.setInt(1, tripID);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                trip = new Trip(ResultSet.getInt(1),ResultSet.getInt(2),ResultSet.getString(3),
                        ResultSet.getDate(4),ResultSet.getTime(5),ResultSet.getString(6), ResultSet.getString(7),
                        ResultSet.getInt(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return trip;
    }

    @Override
    public boolean deleteTrip(int tripID) throws Exception {
        boolean rowDeleted = false;
        try {
            String sql = "delete from trip where tripID = ?";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            PreparedStatement.setInt(1, tripID);
            rowDeleted = PreparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return rowDeleted;
    }

    @Override
    public int getCountTrip() throws Exception {
        int count = 0;
        try {
            String query = "SELECT COUNT(tripID) from trip";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(query);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                count = ResultSet.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return count;
    }

    @Override
    public List<Trip> getListTripPage(int pageIndex, int pageSize) throws SQLException, Exception {
        List<Trip> list = new ArrayList<>();
        try {
            String query = "select * from trip order by tripID offset ? rows fetch first ? rows only";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(query);
            PreparedStatement.setInt(1, (pageIndex - 1) * pageSize);
            PreparedStatement.setInt(2, pageSize);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {

                Trip trip = new Trip(ResultSet.getInt(1), ResultSet.getInt(2), ResultSet.getString(3), ResultSet.getDate(4), ResultSet.getTime(5), ResultSet.getString(6), ResultSet.getString(7), ResultSet.getInt(8));
                list.add(trip);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }

    @Override
    public List<Trip> getTripSearch(String value, Date dateFrom, Date dateTo) throws SQLException, Exception {
        List<Trip> list = new ArrayList<>();
        try {
            String sql = "select * from trip where destination like concat(?,'%') and departureDate between ? and ?";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            PreparedStatement.setString(1, value);
            PreparedStatement.setDate(2, dateFrom);
            PreparedStatement.setDate(3, dateTo);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                Trip trip = new Trip(ResultSet.getInt(1), ResultSet.getInt(2), ResultSet.getString(3), ResultSet.getDate(4), ResultSet.getTime(5), ResultSet.getString(6), ResultSet.getString(7), ResultSet.getInt(8));
                list.add(trip);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }

    @Override
    public int getCountTicketByTripID(int tripID) throws Exception {
        int count = 0;
        try {
            String query = "SELECT COUNT(ticketID) from ticket where tripID = ?";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(query);
            PreparedStatement.setInt(1, tripID);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                count = ResultSet.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return count;
    }

    @Override
    public int getCountOfficeByTripID(int tripID) throws Exception {
        int count = 0;
        try {
            String query = "SELECT COUNT(officeID) from bookingoffice where tripID = ?";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(query);
            PreparedStatement.setInt(1, tripID);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                count = ResultSet.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return count;
    }

    @Override
    public int getCountTripSearch(String value, Date dateFrom, Date dateTo) throws SQLException, Exception {
        int count = 0;
        try {
            String sql = "select count(tripID) from trip where destination like concat(?,'%') and departureDate between ? and ?";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            PreparedStatement.setString(1, value);
            PreparedStatement.setDate(2, dateFrom);
            PreparedStatement.setDate(3, dateTo);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                count = ResultSet.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return count;
    }

    @Override
    public List<Trip> getListTripSearch(String value, Date dateFrom, Date dateTo, int pageIndex, int pageSize)
            throws SQLException, Exception {
        List<Trip> list = new ArrayList<>();
        try {
            String query = "select * from trip where destination like concat(?,'%') and departureDate between ? and ? order by tripID  offset ? rows fetch first ? rows only";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(query);
            PreparedStatement.setString(1, value);
            PreparedStatement.setDate(2, dateFrom);
            PreparedStatement.setDate(3, dateTo);
            PreparedStatement.setInt(4, (pageIndex - 1) * pageSize);
            PreparedStatement.setInt(5, pageSize);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                Trip trip = new Trip(ResultSet.getInt(1), ResultSet.getInt(2), ResultSet.getString(3), ResultSet.getDate(4), ResultSet.getTime(5), ResultSet.getString(6), ResultSet.getString(7), ResultSet.getInt(8));
                list.add(trip);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }

    @Override
    public String getMinDate() throws SQLException, Exception {
        String minDate = "";
        try {
            String sql = "select min(departureDate) from trip";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                minDate = ResultSet.getString(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return minDate;
    }

    @Override
    public String getMaxDate() throws SQLException, Exception {
        String maxDate = "";
        try {
            String sql = "select max(departureDate) from trip";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                maxDate = ResultSet.getString(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return maxDate;
    }

    @Override
    public int getCountTripSearchNoDate(String value) throws SQLException, Exception {
        int count = 0;
        try {
            String sql = "select count(tripID) from trip where destination like concat(?,'%')";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(sql);
            PreparedStatement.setString(1, value);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                count = ResultSet.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return count;
    }

    @Override
    public List<Trip> getListTripSearchNoDate(String value, int pageIndex, int pageSize)
            throws SQLException, Exception {
        List<Trip> list = new ArrayList<>();
        try {
            String query = "select * from trip where destination like concat(?,'%') order by tripID  offset ? rows fetch first ? rows only";
            Connection = SQLConnection.getConnection();
            PreparedStatement = Connection.prepareStatement(query);
            PreparedStatement.setString(1, value);
            PreparedStatement.setInt(2, (pageIndex - 1) * pageSize);
            PreparedStatement.setInt(3, pageSize);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                Trip trip = new Trip(ResultSet.getInt(1), ResultSet.getInt(2), ResultSet.getString(3), ResultSet.getDate(4), ResultSet.getTime(5), ResultSet.getString(6), ResultSet.getString(7), ResultSet.getInt(8));
                list.add(trip);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }

}
