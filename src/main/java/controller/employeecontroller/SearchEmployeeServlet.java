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
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class staffsearchController
 */
@WebServlet("/staffsearchController")
public class SearchEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployeeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"HRM".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();

            String search = request.getParameter("search");
            int id = Integer.parseInt(request.getParameter("filter"));
            List<Employee> f1 = new ArrayList<>();
            List<Employee> f2 = new ArrayList<>();
            final int INDEX_PAGE = Pager.getIndexPage(request.getParameter("id"));

            try {
                f1 = employeeDAO.SearchEmployeessssssss(search, id);
                f2 = employeeDAO.SearchEmployee(INDEX_PAGE, id, search);
                if (f2.isEmpty()) {
                    f2 = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            final int TOTAL = f1.size();
            final int END_PAGE = Pager.getEndPage(TOTAL);

            int issearch = 1;
            request.setAttribute("bulen", issearch);
            request.setAttribute("activeIndex", INDEX_PAGE);
            request.setAttribute("sotrang", END_PAGE);
            request.setAttribute("list", f2);
            request.setAttribute("keySearch", search);
            request.setAttribute("filter", id);

            request.getRequestDispatcher("employee/ListEmployee.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
