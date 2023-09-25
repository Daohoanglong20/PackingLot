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
 * Servlet implementation class staffUpdateController
 */
@WebServlet("/staffUpdateController")
public class UpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployeeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        int id = Integer.parseInt(request.getParameter("id"));
        Employee emp = new Employee();
        try {
            emp = employeeDAO.GetEmployees(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("emp", emp);
        List<String> f1 = new ArrayList<String>();
        try {
            f1 = employeeDAO.GetAllDepartment();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("list", f1);
        request.setAttribute("id", id);
        request.getRequestDispatcher("employee/updateEmployee.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"HRM".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();

            int id = Integer.parseInt(request.getParameter("id"));
            String sDate1 = request.getParameter("date");
            String name = request.getParameter("fname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String account = request.getParameter("account");
            String department = request.getParameter("department");
            String pass = request.getParameter("password");
            boolean sex = false;
            String check = request.getParameter("sex");
            int statis = Integer.parseInt(check);
            if (statis == 0) {
                sex = true;
            } else if (statis == 1) {
                sex = false;
            }
            Employee emp = new Employee(id, account, department, address, sDate1, email, name, phone, sex, pass);

            try {
                employeeDAO.update(emp);
                request.setAttribute("success", "Update employee success.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("fail", "Update employee fail!!!");
            }
            request.getRequestDispatcher("employee/updateEmployee.jsp").forward(request, response);
        }
    }
}
