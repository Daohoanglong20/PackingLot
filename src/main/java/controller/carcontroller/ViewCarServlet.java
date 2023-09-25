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
import java.util.List;

@WebServlet(name = "ViewCarServlet", value = "/ListCar")
public class ViewCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            CarDAOImpl carDAO = new CarDAOImpl();
            List<Car> allCarList = null;
            List<Car> carListPaging = null;

            try {
                allCarList = carDAO.getAllCar();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (allCarList.isEmpty()) {
                carListPaging = null;
            } else {
                String index = request.getParameter("indexPage");
                if (index == null) {
                    index = "1";
                }
                final int INDEX_PAGE = Integer.parseInt(index);
                final int TOTAL_CAR = allCarList.size();
                int endPage = TOTAL_CAR / Pager.CONTENT_PER_PAGE;
                if (TOTAL_CAR % Pager.CONTENT_PER_PAGE != 0) {
                    endPage++;
                }

                try {
                    carListPaging = carDAO.getCarPaging(INDEX_PAGE);
                    request.setAttribute("activeIndex", INDEX_PAGE);
                    request.setAttribute("total", TOTAL_CAR);
                    request.setAttribute("endPage", endPage);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            request.setAttribute("carList", carListPaging);
            request.setAttribute("selectMenu", "viewCar");

            request.getRequestDispatcher("car/ListCar.jsp").forward(request, response);
        }
    }
}
