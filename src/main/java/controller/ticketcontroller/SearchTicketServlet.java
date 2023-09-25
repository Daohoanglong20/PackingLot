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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SearchTicketServlet
 */
@WebServlet("/searchticket")
public class SearchTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTicketServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TicketDAOImpl ticketDAO = new TicketDAOImpl();
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
                    String search = request.getParameter("ename");
                    String filterby = request.getParameter("eselect");

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
                    int totalPage = ticketDAO.getCountTicketSearch(filterby, search);
                    int maxSearchPage = totalPage / pageSize;
                    if (totalPage % pageSize != 0) {
                        maxSearchPage++;
                    }

                    List<Ticket> list = new ArrayList<>();
                    list = ticketDAO.getListTicketSearch(filterby, search, index, pageSize);
                    request.setAttribute("pageSearchCurrentIndex", pageIndex);
                    request.setAttribute("maxSearchPage", maxSearchPage);
                    PrintWriter out = response.getWriter();
                    if (list.isEmpty()) {
                        out.print("<h5>No matches</h5>");
                    } else {
                        out.print("<table class=\"table table-bordered\">");
                        out.print("<thead>\r\n" + "							<tr>\r\n"
                                + "								<th>No</th>\r\n"
                                + "								<th>Trip</th>\r\n"
                                + "								<th>License plate</th>\r\n"
                                + "								<th>Customer</th>\r\n"
                                + "								<th>Booking time</th>\r\n"
                                + "								<th>Action</th>\r\n"
                                + "							</tr>\r\n" + "						</thead>");
                        out.print("<tbody>");
                        int count = (index - 1) * pageSize;
                        for (Ticket ticket : list) {
                            out.print(
                                    "<input type=\"hidden\" name=\"searchValueTicket\" id =\"searchValueTicket\" value=\""
                                            + search + "\">	"
                                            + "<input type=\"hidden\" name=\"selectValueTicket\" id =\"selectValueTicket\" value=\""
                                            + filterby + "\">	"
                                            + "<input type=\"hidden\" name=\"pageSearchCurrentIndex\" id =\"pageSearchCurrentIndex\" value=\""
                                            + pageIndex + "\"> " + "<tr>\r\n"
                                            + "									<td>" + ++count + "</td>\r\n"
                                            + "									<td>" + ticket.getTripName()
                                            + "</td>\r\n" + "									<td>"
                                            + ticket.getLicensePlate() + "</td>\r\n"
                                            + "									<td>" + ticket.getCustomerName()
                                            + "</td>\r\n" + "									<td>"
                                            + ticket.getBookingTime() + "</td>\r\n"
                                            + "									<td><a href=\"deleteticket?id="
                                            + ticket.getTicketID() + "\"onclick=\"return confirm('Are you sure you want to delete this item?');\"><i\r\n"
                                            + "												class=\"fa fa-trash\" aria-hidden=\"true\"></i>Delete</a> " + " <a href=\"detailticket?id="
                                            + ticket.getTicketID() + "\">View</a></td>" + "</tr>");
                        }

                        out.print("</tbody>\r\n" + "</table>");
                        out.print("<nav aria-label=\"paging\">\r\n"
                                + "							<input type=\"hidden\" name=\"maxSearchPage\" id=\"maxSearchPage\"\r\n"
                                + "								value=" + maxSearchPage + ">\r\n"
                                + "							<ul class=\"pagination\">\r\n"
                                + "								<li class=\"page-item\"><a class=\"page-link\"\r\n"
                                + "									onclick=\"btnSearchTicketPrevious()\" href=\"#\">Previous</a></li>");
                        for (int i = 1; i <= maxSearchPage; i++) {
                            out.print("<li class=\"page-item\" aria-current=\"page\"><a\r\n"
                                    + "										class=\"page-link\" id=\"currentSearchPage"
                                    + i + "\"\r\n" + "onclick=\"pagingTicketSearch(" + i + ")\""
                                    + "										\" href=\"#\">" + i + "</a></li>");
                        }
                        out.print("<li class=\"page-item\"><a class=\"page-link\"\r\n"
                                + "									onclick=\"btnSearchTicketNext()\" href=\"#\">Next</a></li>\r\n"
                                + "							</ul>\r\n" + "</nav>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
