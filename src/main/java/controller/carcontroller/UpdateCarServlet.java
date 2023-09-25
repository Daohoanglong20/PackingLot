package controller.carcontroller;

import common.Pager;
import dao.Impl.CarDAOImpl;
import model.Car;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

@WebServlet(name = "UpdateCarServlet", value = "/updateCar")
public class UpdateCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String license = request.getParameter("license");

        CarDAOImpl carDAO = new CarDAOImpl();
        try {
            Car car = carDAO.getCarByLicensePlate(license);
            request.setAttribute("car", car);
            request.getRequestDispatcher("car/updateCar.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            String license = request.getParameter("license");
            String carType = request.getParameter("carType");
            String carColor = request.getParameter("carColor");
            String company = request.getParameter("company");
            String parkingLot = request.getParameter("parkinglot");
            int parkId = Integer.parseInt(parkingLot);

            CarDAOImpl carDAO = new CarDAOImpl();


            Car car = new Car(license, carColor, carType.toUpperCase(Locale.ROOT), company, parkId);
            try {
                if (carDAO.update(car)) {
                    request.setAttribute("success", "Update car successful.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("fail", "Update car fail!!!");
            }
            request.setAttribute("car", car);
            request.getRequestDispatcher("car/updateCar.jsp").forward(request, response);
        }
    }
}
