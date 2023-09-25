package controller.ticketcontroller;

import dao.Impl.EmployeeDAOImpl;
import dao.Impl.TicketDAOImpl;
import model.Employee;
import model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class PagingTicketServlet
 */
@WebServlet("/pagingticket")
public class PagingTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagingTicketServlet() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
                    TicketDAOImpl ticketDAO = new TicketDAOImpl();
                    List<Ticket> list = ticketDAO.getAllTicket(index, pageSize);
                    request.setAttribute("pageCurrentIndex", pageIndex);
                    PrintWriter out = response.getWriter();
                    int count = (index - 1) * pageSize;
                    for (Ticket ticket : list) {
                        out.print("<input type=\"hidden\" name=\"pageCurrentIndex\"\r\n"
                                + "id=\"pageCurrentIndex\" value=\"" + pageIndex + "\">" + "<tr>\r\n"
                                + "									<td>" + ++count + "</td>\r\n"
                                + "									<td>" + ticket.getTripName() + "</td>\r\n"
                                + "									<td>" + ticket.getLicensePlate() + "</td>\r\n"
                                + "									<td>" + ticket.getCustomerName() + "</td>\r\n"
                                + "									<td>" + ticket.getBookingTime() + "</td>\r\n"
                                + "									<td><a href=\"deleteticket?id="
                                + ticket.getTicketID() + "\"onclick=\"return confirm('Are you sure you want to delete this item?');\"><i\r\n"
                                + "												class=\"fa fa-trash\" aria-hidden=\"true\"></i>Delete</a> " + " <a href=\"detailticket?id="
                                + ticket.getTicketID() + "\">View</a></td>" + "</tr>");
                    }
                }
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

        doGet(request, response);
    }

}
