package controller.tripcontroller;

import dao.Impl.EmployeeDAOImpl;
import dao.Impl.TripDAOImpl;
import model.Employee;
import model.Trip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/trippaging")
public class TripPagingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();


    public TripPagingServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TripDAOImpl tripDAO = new TripDAOImpl();
        try {
            Cookie[] cookies = request.getCookies();
            Cookie userCookie = null;
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equalsIgnoreCase("userCookie")) {
                        userCookie = cookies[i];
                    }
                }
                if (userCookie == null) {
                    response.sendRedirect("Login");
                } else {
                    Employee em = employeeDAO.GetEmployees(Integer.parseInt(userCookie.getValue()));
                    if (!em.getDepartment().equalsIgnoreCase("TRIP")) {
                        request.getRequestDispatcher("common/block.jsp").forward(request, response);
                        return;
                    }
                    request.setAttribute("user", em.getAccount());

                    String pageIndex = request.getParameter("pageIndex");
                    if (pageIndex == null) {
                        pageIndex = "1";
                    }
                    int index = 0;

                    try {
                        index = Integer.parseInt(pageIndex);
                    } catch (Exception e) {
                        index = 1;
                    }

                    int pageSize = 4;
                    List<Trip> list = tripDAO.getListTripPage(index, pageSize);
                    request.setAttribute("pageTripCurrentIndex", pageIndex);
                    PrintWriter out = response.getWriter();
                    int count = (index - 1) * pageSize;
                    for (Trip trip : list) {
                        out.print("<input type=\"hidden\" name=\"pageTripCurrentIndex\" id=\"pageTripCurrentIndex\"\r\n"
                                + "	value=\"" + pageIndex + "\">\r\n"
                                + "	<tr>\r\n" + "<th>"
                                + (++count) + "</th>\r\n" + "<td>"
                                + trip.getDestination() + "</td>\r\n" + "<td>"
                                + trip.getDepartureTime() + "</td>\r\n" + "<td>"
                                + trip.getDriver() + "</td>\r\n" + "<td>"
                                + trip.getCarType() + "</td>\r\n" + "<td>"
                                + trip.getBookedTicketNumber() + "</td>\r\n"
                                + "	<td>\r\n"
                                + "<a href=\"tripdetails?id=" + trip.getTripID()
                                + "\">\r\n"
                                + "<i class=\"fa fa-search\" aria-hidden=\"true\"></i>&nbsp;Edit</a>&ensp;\r\n"
                                + "	<a href=\"deletetrip?id=" + trip.getTripID()
                                + "\"\r\n"
                                + "	onclick=\"return confirm('Are you sure you want to delete this item?');\">\r\n"
                                + "	<i class=\"fa fa-times\" aria-hidden=\"true\"></i>&nbsp;Detele</a>\r\n"
                                + "	</td>\r\n" + "</tr>");
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("Error", e);
            request.getRequestDispatcher("common/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}