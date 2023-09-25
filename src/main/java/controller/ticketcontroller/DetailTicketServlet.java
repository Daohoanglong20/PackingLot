package controller.ticketcontroller;

import common.Pager;
import dao.CarDAO;
import dao.Impl.CarDAOImpl;
import dao.Impl.EmployeeDAOImpl;
import dao.Impl.TicketDAOImpl;
import dao.Impl.TripDAOImpl;
import dao.TicketDAO;
import dao.TripDAO;
import model.Car;
import model.Employee;
import model.Ticket;
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

/**
 * Servlet implementation class DetailTicketServlet
 */
@WebServlet("/detailticket")
public class DetailTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailTicketServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                int ticketId = Integer.parseInt(request.getParameter("id"));
                Ticket ticket = new TicketDAOImpl().getTicketByID(ticketId);
                request.setAttribute("customerName", ticket.getCustomerName());
                request.setAttribute("ticketId", ticket.getTicketID());
                request.setAttribute("time", ticket.getBookingTime());
                request.setAttribute("tripName", ticket.getTripName());
                request.setAttribute("license", ticket.getLicensePlate());
                request.getRequestDispatcher("ticket/updateTicket.jsp").forward(request,
                        response);
            }
        } catch (Exception e) {
            
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Employee employee = Pager.getEmployeeFromSession(request, response);
            if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
                response.sendRedirect("common/block.jsp");
            } else {
                int ticketId = Integer.parseInt(request.getParameter("ticketId"));
                String customer = request.getParameter("customer");
                java.util.Date d = new SimpleDateFormat("HH:mm", Locale.TAIWAN)
                        .parse(request.getParameter("time"));
                java.sql.Time time = new java.sql.Time(d.getTime());
                int tripId = Integer.parseInt(request.getParameter("trip"));
                String licensePlate = request.getParameter("licenseplate");

                Ticket ticket = new Ticket(ticketId, time, customer, licensePlate, tripId);
                TicketDAO ticketDAO = new TicketDAOImpl();
                try {
                    ticketDAO.updateTicket(ticket);
                    request.setAttribute("success", "Update Ticket successful");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("fail", "Update Ticket fail!!!");
                }
                request.getRequestDispatcher("ticket/updateTicket.jsp").forward(request, response);
            }
        } catch (
                Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("common/error.jsp").forward(request, response);
        }
    }

}
