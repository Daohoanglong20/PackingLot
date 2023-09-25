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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ViewPackinglotServlet
 */
@WebServlet("/ViewPackinglotServlet")
public class ViewPackinglotServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPackinglotServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            ParkingLotDAO parkingLotDAO = new ParkingLotDAOImpl();
            List<ParkingLot> listP = new ArrayList<ParkingLot>();
            List<ParkingLot> listPaging = new ArrayList<ParkingLot>();

            final int INDEX_PAGE = Pager.getIndexPage(request.getParameter("id"));
            try {
                listP = parkingLotDAO.GetAllParkingLot();
                listPaging = parkingLotDAO.getParkingPaging(INDEX_PAGE);
                if (listPaging.isEmpty()) {
                    listPaging = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            final int TOTAL = listP.size();
            final int END_PAGE = Pager.getEndPage(TOTAL);

            int issearch = 0;
            request.setAttribute("bulen", issearch);
            request.setAttribute("currentPage", INDEX_PAGE);
            request.setAttribute("numberPage", END_PAGE);
            request.setAttribute("listP", listPaging);
            request.getRequestDispatcher("parkinglot/ListParkingLot.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
