package controller.ticketcontroller;

import common.Pager;
import dao.Impl.TicketDAOImpl;
import dao.TicketDAO;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TicketListServlet
 */
@WebServlet("/ticketlist")
public class TicketListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketListServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            Employee employee = Pager.getEmployeeFromSession(request, response);
            if (!"TRIP".equalsIgnoreCase(employee.getDepartment())) {
                response.sendRedirect("common/block.jsp");
            } else {
                TicketDAO ticketDAO = new TicketDAOImpl();
                final int INDEX_PAGE = Pager.getIndexPage(request.getParameter("pageIndex"));

                final int TOTAL = ticketDAO.getCountTicket();
                final int END_PAGE = Pager.getEndPage(TOTAL);

                request.setAttribute("pageCurrentIndex", 1);
                request.setAttribute("listTicket", new TicketDAOImpl().getAllTicket(INDEX_PAGE, Pager.CONTENT_PER_PAGE));
                int issearch = 0;
                request.setAttribute("bulen", issearch);
                request.setAttribute("numberPage", END_PAGE);
                request.setAttribute("currentPage", INDEX_PAGE);
                request.getRequestDispatcher("ticket/ListTicket.jsp").forward(request, response);
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
