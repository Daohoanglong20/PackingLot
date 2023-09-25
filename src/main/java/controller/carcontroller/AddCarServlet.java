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

@WebServlet(name = "AddCarServlet", value = "/addCar")
public class AddCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("selectMenu", "addCar");
        req.getRequestDispatcher("car/addCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            CarDAOImpl carDAO = new CarDAOImpl();

            String license = request.getParameter("license");
            String carType = request.getParameter("carType");
            String carColor = request.getParameter("carColor");

            Car checkExistCar = null;
            try {
                checkExistCar = carDAO.getCarByLicensePlate(license);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (checkExistCar == null) {
                String company = request.getParameter("company");
                String parkinglot = request.getParameter("parkinglot");
                int parkingLotId = Integer.parseInt(parkinglot);

                Car car = new Car(license, carColor, carType.toUpperCase(Locale.ROOT), company, parkingLotId);
                try {
                    if (carDAO.add(car)) {
                        request.setAttribute("success", "Add new car successful.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("fail", "Add new car fail!!!");
                }
            } else {
                request.setAttribute("existed", "Car License already existed!!!");
                request.setAttribute("license", license);
                request.setAttribute("carType", carType);
                request.setAttribute("carColor", carColor);
                request.setAttribute("selectMenu", "addCar");
            }
            request.getRequestDispatcher("car/addCar.jsp").forward(request, response);
        }
    }
}
