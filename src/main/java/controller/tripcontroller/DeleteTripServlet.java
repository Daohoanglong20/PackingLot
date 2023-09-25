package controller.tripcontroller;

import common.Pager;
import dao.Impl.EmployeeDAOImpl;
import dao.Impl.TripDAOImpl;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DeleteTripServlet
 */
@WebServlet("/deletetrip")
public class DeleteTripServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public DeleteTripServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            Employee employee = Pager.getEmployeeFromSession(request, response);
            if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
                response.sendRedirect("common/block.jsp");
            } else {
                TripDAOImpl tripDAO = new TripDAOImpl();

                int tripID = Integer.parseInt(request.getParameter("id"));
                int countTicket = tripDAO.getCountTicketByTripID(tripID);
                int countOffice = tripDAO.getCountOfficeByTripID(tripID);
                if (countTicket == 0 && countOffice == 0) {
                    tripDAO.deleteTrip(tripID);
                    request.setAttribute("success", "Delete successful.");
                } else {
                    request.setAttribute("fail", "Cannot delete this trip.");
                }
                request.getRequestDispatcher("triplist?indexPage=1").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("common/error.jsp").forward(request, response);
        }
    }

}