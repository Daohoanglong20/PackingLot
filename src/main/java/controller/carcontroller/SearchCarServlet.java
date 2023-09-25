package controller.carcontroller;

import common.Pager;
import dao.CarDAO;
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

@WebServlet(name = "SearchCarServlet", value = "/search-car")
public class SearchCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            String keySearch = request.getParameter("keySearch").trim();
            String filter = request.getParameter("filter");
            String index = request.getParameter("indexPage");

            CarDAO carDAO = new CarDAOImpl();
            List<Car> carList = null;
            try {
                carList = carDAO.getAllCarBySearch(keySearch, filter);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            final int TOTAL_CAR = carList.size();
            final int END_PAGE = Pager.getEndPage(TOTAL_CAR);
            final int INDEX_PAGE = Pager.getIndexPage(index);

            try {
                carList = carDAO.getCarPagingBySearch(keySearch, filter, INDEX_PAGE);
                if (carList.isEmpty()) {
                    carList = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("carList", carList);
            request.setAttribute("keySearch", keySearch);
            request.setAttribute("filter", filter);
            request.setAttribute("endPage", END_PAGE);
            request.setAttribute("activeIndex", INDEX_PAGE);
            request.getRequestDispatcher("car/ListCar.jsp").forward(request, response);
        }
    }
}
