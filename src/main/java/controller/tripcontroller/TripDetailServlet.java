package controller.tripcontroller;

import common.Pager;
import dao.Impl.EmployeeDAOImpl;
import dao.Impl.TripDAOImpl;
import model.Employee;
import model.Trip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Servlet implementation class TripDetailServlet
 */
@WebServlet("/tripdetails")
public class TripDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TripDAOImpl tripDAO = new TripDAOImpl();
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public TripDetailServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            Employee employee = Pager.getEmployeeFromSession(request, response);
            if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
                response.sendRedirect("common/block.jsp");
            } else {
                int tripID = Integer.parseInt(request.getParameter("id"));
                Trip trip = tripDAO.getTripByID(tripID);

                request.setAttribute("tripID", trip.getTripID());
                request.setAttribute("destination", trip.getDestination());
                request.setAttribute("driver", trip.getDriver());
                request.setAttribute("cartype", trip.getCarType());
                request.setAttribute("maxnumberticket", trip.getMaximumOnlineTicketNumber());
                request.setAttribute("bookedticketnumber", trip.getBookedTicketNumber());
                String time = new SimpleDateFormat("HH:mm", Locale.TAIWAN).format(trip.getDepartureTime());
                request.setAttribute("departuretime", time);
                String date = new SimpleDateFormat("yyyy-MM-dd").format(trip.getDepartureDate());
                request.setAttribute("departuredate", date);
                request.getRequestDispatcher("trip/updateTrip.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("common/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Employee employee = Pager.getEmployeeFromSession(request, response);
            if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
                response.sendRedirect("common/block.jsp");
            } else {
                int tripID = Integer.parseInt(request.getParameter("tripID"));
                String destination = request.getParameter("destination");
                String driver = request.getParameter("driver");
                int maximumOnlineTicketNumber = Integer.parseInt(request.getParameter("maxnumberticket"));
                String cartype = request.getParameter("cartype");

                java.util.Date d = new SimpleDateFormat("HH:mm", Locale.TAIWAN)
                        .parse(request.getParameter("departuretime"));
                java.sql.Time departuretime = new java.sql.Time(d.getTime());

                String departureDate = request.getParameter("departuredate");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
                Date departuredate = new java.sql.Date(date.getTime());
                tripDAO.updateTrip(destination,driver,maximumOnlineTicketNumber,cartype,departuredate,departuretime,tripID);
                request.setAttribute("success", "Update Trip successful");
                request.getRequestDispatcher("trip/updateTrip.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("common/error.jsp").forward(request, response);
        }
    }

}