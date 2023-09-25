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
 * Servlet implementation class staffAddEmployee
 */
@WebServlet("/staffAddEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doPost(request, response);
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
            String sDate1 = request.getParameter("date");
            String name = request.getParameter("fname");

            String phone = request.getParameter("phone");

            String address = request.getParameter("address");

            String email = request.getParameter("email");
            String account = request.getParameter("account");
            String department = request.getParameter("department");
            boolean sex = false;
            String check = request.getParameter("sex");
            String pass = request.getParameter("password");

            int statis = Integer.parseInt(check);
            if (statis == 0) {
                sex = true;
            } else if (statis == 1) {
                sex = false;
            }
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            Employee checkExistAccount = null;
            try {
                checkExistAccount = employeeDAO.GetEmployeeByAccount(account);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (checkExistAccount != null) {
                request.setAttribute("messAcc", "Account is already existed!!!");

                request.setAttribute("name", name);
                request.setAttribute("phone", phone);
                request.setAttribute("sex", statis);
                request.setAttribute("address", address);
                request.setAttribute("email", email);
                request.setAttribute("account", account);
                request.setAttribute("password", pass);
                request.setAttribute("department", department);
            } else {
                Employee emp = new Employee(account, department, address, sDate1, email, name, phone, sex, pass);
                int n = 0;
                try {
                    n = employeeDAO.staffAddEmployee(emp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (n > 0) {
                    request.setAttribute("success", "Add employee successful.");
                } else {
                    request.setAttribute("fail", "Add employee fail!!!");
                }
            }
            request.getRequestDispatcher("employee/addEmployee.jsp").forward(request, response);
        }
    }
}
