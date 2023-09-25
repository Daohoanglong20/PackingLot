package controller.packinglotcontroller;

import common.Pager;
import dao.Impl.ParkingLotDAOImpl;
import dao.ParkingLotDAO;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class DeletePackinglotServlet
 */
@WebServlet("/DeletePackinglotServlet")
public class DeletePackinglotServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePackinglotServlet() {
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
            try {
                parkingLotDAO.deleteParkingLot(id);
                request.setAttribute("success", "Delete successful.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("fail", "Delete fail!!!");
            }
            request.getRequestDispatcher("ViewPackinglotServlet?id=1").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
