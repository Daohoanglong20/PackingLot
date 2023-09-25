package controller.employeecontroller;

import common.Pager;
import dao.EmployeeDAO;
import dao.Impl.EmployeeDAOImpl;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class staffdeleteController
 */
@WebServlet("/staffdeleteController")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployeeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"HRM".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                employeeDAO.delete(id);
                request.setAttribute("success", "Delete employee (ID: " + id + ") successful.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("fail", "Delete employee (ID: " + id + ") fail!!.");
            }
            request.getRequestDispatcher("staffEmployeeList?id=1").forward(request, response);
        }
    }
}
