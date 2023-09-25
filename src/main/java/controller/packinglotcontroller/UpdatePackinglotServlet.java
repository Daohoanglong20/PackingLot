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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class UpdatePackinglotServlet
 */
@WebServlet("/UpdatePackinglotServlet")
public class UpdatePackinglotServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePackinglotServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            ParkingLotDAO parkingLotDAO = new ParkingLotDAOImpl();
            int id = Integer.parseInt(request.getParameter("id"));
            ParkingLot parkingLot = new ParkingLot();
            try {
                parkingLot = parkingLotDAO.getParkingLotById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("parkingLot", parkingLot);
            List<String> f1 = new ArrayList<String>();

            request.setAttribute("list", f1);
            request.setAttribute("id", id);
            request.getRequestDispatcher("parkinglot/updateParkingLot.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            ParkingLotDAO parkingLotDAO = new ParkingLotDAOImpl();
            int parkId = Integer.parseInt(request.getParameter("parkId"));
            String parkName = request.getParameter("parkingName");
            String parkPlace = request.getParameter("parkPlace");
            int parkArea = Integer.parseInt(request.getParameter("area"));
            double parkPrice = Double.parseDouble(request.getParameter("price"));
            String parkStatus = request.getParameter("status");
            ParkingLot parkingLot = new ParkingLot(parkId, parkName, parkPlace, parkArea, parkPrice, parkStatus);
            try {
                parkingLotDAO.updateParkingLot(parkingLot);
                request.setAttribute("success", "Update Parking lot successful.");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("fail", "Update Parking lot fail!!!");
            }
            request.setAttribute("parkingLot", parkingLot);
            request.getRequestDispatcher("parkinglot/updateParkingLot.jsp").forward(request, response);
        }
    }
}
