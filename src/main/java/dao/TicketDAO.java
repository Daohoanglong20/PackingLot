package dao;

import model.Ticket;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface TicketDAO {
    public List<Ticket> getAllTicket(int pageIndex, int pageSize) throws SQLException, Exception;

    public Ticket getTicketByID(int id) throws Exception;

    public void addNew(Time bookingTime, String customerName, String licensePlate, int tripID) throws SQLException;

    public void deleteTicket(int id) throws SQLException;

    public void deleteTicketByLicenseCar(String licensePlate) throws SQLException;

    public List<Ticket> getTicket(String value, String filter) throws Exception;

    public int getCountTicket() throws Exception;

    public int getCountTicketSearch(String filter, String value) throws Exception;

    public List<Ticket> getListTicketSearch(String filter, String value, int pageIndex, int pageSize) throws SQLException;

    void updateTicket(Ticket ticket) throws SQLException;

}
