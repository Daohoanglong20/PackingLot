package controller.carcontroller;

import common.Pager;
import dao.Impl.CarDAOImpl;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteCarServlet", value = "/delete-car")
public class DeleteCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            String license = request.getParameter("license");

            CarDAOImpl carDAO = new CarDAOImpl();
            try {
                if (carDAO.delete(license)) {
                    request.setAttribute("success", "Delete car (License plate: " + license + ") successful.");
                } else {
                    request.setAttribute("fail", "Delete car (License plate: " + license + ") fail!!!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("fail", "Delete car (License plate: " + license + ") fail!!!");
            }
            request.getRequestDispatcher("/ListCar").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
