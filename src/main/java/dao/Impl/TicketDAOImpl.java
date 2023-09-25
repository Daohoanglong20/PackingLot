package dao.Impl;


import common.SQLConnection;
import dao.TicketDAO;
import model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDAOImpl implements TicketDAO {
    SQLConnection SQLConnection = new SQLConnection();
    private Connection conn = null;
    private PreparedStatement PreparedStatement = null;
    private ResultSet ResultSet = null;

    @Override
    public List<Ticket> getAllTicket(int pageIndex, int pageSize) throws SQLException, Exception {
        List<Ticket> list = new ArrayList<>();
        try {
            String query = "select ticket.ticketID,ticket.bookingTime,ticket.customerName,ticket.licensePlate,ticket.tripID,trip.destination "
                    + "as 'tripDes' from ticket,trip where ticket.tripID = trip.tripID "
                    + " order by ticketID offset ? rows fetch first ? rows only";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
            PreparedStatement.setInt(1, (pageIndex - 1) * pageSize);
            PreparedStatement.setInt(2, pageSize);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                Ticket p = new Ticket(ResultSet.getInt(1), ResultSet.getTime(2), ResultSet.getString(3), ResultSet.getString(4), ResultSet.getInt(5),
                        ResultSet.getString(6));
                list.add(p);
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
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public Ticket getTicketByID(int id) throws Exception {
        Ticket ticket = new Ticket();
        try {
            String sql = "select ticket.ticketID,ticket.bookingTime,ticket.customerName,ticket.licensePlate,ticket.tripID,trip.destination "
                    + "as 'tripDes' from ticket,trip where ticket.tripID = trip.tripID and ticketID = ?";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(sql);
            PreparedStatement.setInt(1, id);
            ResultSet = PreparedStatement.executeQuery();
            if (ResultSet.next()) {
                ticket = new Ticket(ResultSet.getInt(1), ResultSet.getTime(2), ResultSet.getString(3), ResultSet.getString(4), ResultSet.getInt(5),
                        ResultSet.getString(6));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return ticket;
    }

    @Override
    public void addNew(Time bookingTime, String customerName, String licensePlate, int tripID) throws SQLException {
        try {
            String query = "INSERT INTO [dbo].[ticket]([bookingTime],[customerName],[licensePlate],[tripID]) VALUES (?,?,?,?)";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
            PreparedStatement.setTime(1, bookingTime);
            PreparedStatement.setString(2, customerName);
            PreparedStatement.setString(3, licensePlate);
            PreparedStatement.setInt(4, tripID);
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
            if (conn != null) {
                conn.close();
            }
        }

    }

    @Override
    public void deleteTicket(int id) throws SQLException {
        try {
            String query = "DELETE FROM [dbo].[ticket]\r\n" + "WHERE ticketID = ?";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
            PreparedStatement.setInt(1, id);
            PreparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    @Override
    public void deleteTicketByLicenseCar(String licensePlate) throws SQLException {
        try {
            String query = "DELETE FROM ticket WHERE licensePlate = ?";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
            PreparedStatement.setString(1, licensePlate);
            PreparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<Ticket> getTicket(String value, String filter) throws Exception {
        List<Ticket> list = new ArrayList<>();
        try {
            String query = "select ticket.ticketID,ticket.bookingTime,ticket.customerName,ticket.licensePlate,ticket.tripID,trip.destination \r\n"
                    + "as 'tripDes' from ticket,trip where ticket.tripID = trip.tripID \r\n" + "and " + filter
                    + " LIKE CONCAT(?,'%')";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
            PreparedStatement.setString(1, value);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                Ticket ticket = new Ticket(ResultSet.getInt(1), ResultSet.getTime(2), ResultSet.getString(3), ResultSet.getString(4), ResultSet.getInt(5),
                        ResultSet.getString(6));
                list.add(ticket);
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
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public int getCountTicket() throws Exception {
        int count = 0;
        try {
            String query = "select COUNT(ticketID) from ticket";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
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
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    @Override
    public int getCountTicketSearch(String filter, String value) throws Exception {
        int count = 0;
        try {
            String query = "select COUNT(ticketID) from ticket where " + filter + "\r\n"
                    + "LIKE CONCAT(?,'%')";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
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
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    @Override
    public List<Ticket> getListTicketSearch(String filter, String value, int pageIndex, int pageSize)
            throws SQLException {
        List<Ticket> list = new ArrayList<>();
        try {
            String query = "select ticket.ticketID,ticket.bookingTime,ticket.customerName,ticket.licensePlate,ticket.tripID,trip.destination \r\n"
                    + "as 'tripDes' from ticket,trip where ticket.tripID = trip.tripID \r\n"
                    + "and " + filter + "\r\n"
                    + "LIKE CONCAT(?,'%')\r\n"
                    + "order by ticketID offset ? rows fetch first ? rows only";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
            PreparedStatement.setString(1, value);
            PreparedStatement.setInt(2, (pageIndex - 1) * pageSize);
            PreparedStatement.setInt(3, pageSize);
            ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                Ticket p = new Ticket(ResultSet.getInt(1), ResultSet.getTime(2), ResultSet.getString(3), ResultSet.getString(4), ResultSet.getInt(5),
                        ResultSet.getString(6));
                list.add(p);
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
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void updateTicket(Ticket ticket) throws SQLException {
        try {
            String query = "UPDATE ticket SET bookingTime =?, customerName =?, licensePlate =?, tripId = ? WHERE ticketId =?";
            conn = SQLConnection.getConnection();
            PreparedStatement = conn.prepareStatement(query);
            PreparedStatement.setTime(1, ticket.getBookingTime());
            PreparedStatement.setString(2, ticket.getCustomerName());
            PreparedStatement.setString(3, ticket.getLicensePlate());
            PreparedStatement.setInt(4, ticket.getTripID());
            PreparedStatement.setInt(5, ticket.getTicketID());
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
            if (conn != null) {
                conn.close();
            }
        }
    }
}
