package controller.packinglotcontroller;

import common.Pager;
import dao.Impl.ParkingLotDAOImpl;
import dao.ParkingLotDAO;
import model.Employee;
import model.ParkingLot;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AddPackinglotServlet
 */
@WebServlet("/AddPackinglotServlet")
public class AddPackinglotServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPackinglotServlet() {
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
        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            String parkName = request.getParameter("parkingName");
            String parkPlace = request.getParameter("parkPlace");
            int parkArea = Integer.parseInt(request.getParameter("area"));
            double parkPrice = Double.parseDouble(request.getParameter("price"));
            String parkStatus = request.getParameter("status");
            ParkingLot parkingLot = new ParkingLot(parkName, parkPlace, parkArea, parkPrice, parkStatus);
            ParkingLotDAO parkingLotDAO = new ParkingLotDAOImpl();

            try {
                int n = parkingLotDAO.AddParkingLot(parkingLot);
                request.setAttribute("success", "add ParkingLot successful");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("fail", "add ParkingLot fail!!!");
            }
            request.getRequestDispatcher("parkinglot/addParkingLot.jsp").forward(request, response);
        }
    }
}
