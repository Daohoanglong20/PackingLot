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
import java.util.List;


/**
 * Servlet implementation class staffEmployeeList
 */
@WebServlet("/staffEmployeeList")
public class ViewEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployeeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"HRM".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            String index = request.getParameter("id");
            List<Employee> f1 = null;
            List<Employee> f2 = null;
            final int INDEX_PAGE = Pager.getIndexPage(index);

            try {
                f1 = employeeDAO.GetAllEmployees();
                f2 = employeeDAO.GetEmployeesTheoTrang(INDEX_PAGE);
                if (f2.isEmpty()) {
                    f2 = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            final int TOTAL = f1.size();
            final int END_PAGE = Pager.getEndPage(TOTAL);

            int issearch = 0;
            request.setAttribute("bulen", issearch);
            request.setAttribute("activeIndex", INDEX_PAGE);
            request.setAttribute("sotrang", END_PAGE);
            request.setAttribute("list", f2);
            request.getRequestDispatcher("employee/ListEmployee.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
