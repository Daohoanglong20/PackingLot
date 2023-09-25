package controller.ticketcontroller;

import common.Pager;
import dao.Impl.EmployeeDAOImpl;
import dao.Impl.TicketDAOImpl;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class DeleteTicketServlet
 */
@WebServlet("/deleteticket")
public class DeleteTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTicketServlet() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
                int id = Integer.parseInt(request.getParameter("id"));
                new TicketDAOImpl().deleteTicket(id);
                request.setAttribute("success", "Delete succesful.");
                request.getRequestDispatcher("ticketlist?indexPage=1").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
