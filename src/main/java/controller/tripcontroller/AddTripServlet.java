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
 * Servlet implementation class AddTripServlet
 */
@WebServlet("/addtrip")
public class AddTripServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TripDAOImpl tripDAO = new TripDAOImpl();
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public AddTripServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            request.getRequestDispatcher("trip/addTrip.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            Employee employee = Pager.getEmployeeFromSession(request, response);
            if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
                response.sendRedirect("common/block.jsp");
            } else {
                int bookedTicketNumber = 0;
                String carType = request.getParameter("cartype");
                String destination = request.getParameter("destination");
                String driver = request.getParameter("driver");
                int maximumOnlineTicketNumber = Integer.parseInt(request.getParameter("maxnumberticket"));

                java.util.Date d = new SimpleDateFormat("HH:mm", Locale.TAIWAN)
                        .parse(request.getParameter("departuretime"));
                java.sql.Time departuretime = new java.sql.Time(d.getTime());

                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd")
                        .parse(request.getParameter("departuredate"));
                Date departuredate = new java.sql.Date(date.getTime());

                Trip trip = new Trip(maximumOnlineTicketNumber, bookedTicketNumber, carType, departuredate,
                        departuretime, destination, driver, maximumOnlineTicketNumber);

                try {
                    tripDAO.addTrip(trip);
                    request.setAttribute("success", "Add Trip successful");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("fail", "Add Trip fail!!!");
                }
                request.getRequestDispatcher("trip/addTrip.jsp").forward(request, response);
            }
        } catch (
                Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("common/error.jsp").forward(request, response);

        }
    }
}