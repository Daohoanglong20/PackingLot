package controller.ticketcontroller;

import common.Pager;
import dao.CarDAO;
import dao.Impl.CarDAOImpl;
import dao.Impl.TicketDAOImpl;
import dao.Impl.TripDAOImpl;
import dao.TripDAO;
import model.Car;
import model.Employee;
import model.Trip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "AddTicketServlet2", value = "/addTicket")
public class AddTicketServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            TripDAO tripDAO = new TripDAOImpl();
            CarDAO carDAO = new CarDAOImpl();
            List<Car> carList = null;
            List<Trip> tripList = null;

            try {
                carList = carDAO.getAllCar();
                tripList = tripDAO.getListTrip();
                request.setAttribute("carList", carList);
                request.setAttribute("tripList", tripList);
                request.getRequestDispatcher("ticket/addTicket.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Employee employee = Pager.getEmployeeFromSession(request, response);
            if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
                response.sendRedirect("common/block.jsp");
            } else {
                String customer = request.getParameter("customer");
                java.util.Date d = new SimpleDateFormat("HH:mm", Locale.TAIWAN).parse(request.getParameter("time"));
                java.sql.Time getTime = new java.sql.Time(d.getTime());
                int trip = Integer.parseInt(request.getParameter("trip"));
                String licenseplate = request.getParameter("licenseplate");
                TicketDAOImpl ticketDAO = new TicketDAOImpl();
                ticketDAO.addNew(getTime, customer, licenseplate, trip);
                TripDAO tripDAO = new TripDAOImpl();
                tripDAO.updateBookedTicketByTripId(trip);
                request.setAttribute("success", "Add Ticket successful.");
                request.getRequestDispatcher("ticket/addTicket.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("common/error.jsp");
        }
    }
}
