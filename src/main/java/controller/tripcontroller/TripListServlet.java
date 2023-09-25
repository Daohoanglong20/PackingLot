package controller.tripcontroller;

import common.Pager;
import dao.Impl.TripDAOImpl;
import model.Employee;
import model.Trip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TripListServlet", value = "/triplist")
public class TripListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TripDAOImpl tripDAO = new TripDAOImpl();

    public TripListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            
            Employee employee = Pager.getEmployeeFromSession(request, response);
            if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
                response.sendRedirect("common/block.jsp");
            } else {
                final int INDEX_PAGE = Pager.getIndexPage(request.getParameter("pageIndex"));

                final int TOTAL = tripDAO.getCountTrip();
                final int END_PAGE = Pager.getEndPage(TOTAL);

                request.setAttribute("pageTripCurrentIndex", 1);

                List<Trip> triplist = tripDAO.getListTripPage(INDEX_PAGE, Pager.CONTENT_PER_PAGE);
                int issearch = 0;
                request.setAttribute("bulen", issearch);
                request.setAttribute("numberPage", END_PAGE);
                request.setAttribute("currentPage", INDEX_PAGE);
                request.setAttribute("triplist", triplist);
                request.getRequestDispatcher("trip/viewTrip.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("common/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
