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
 * Servlet implementation class SearchParkinglotServlet
 */
@WebServlet("/SearchParkinglotServlet")
public class SearchParkinglotServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchParkinglotServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String search = (String) request.getSession().getAttribute("search1");
        int id = (int) request.getSession().getAttribute("search2");

        ParkingLotDAO parkingLotDAO = new ParkingLotDAOImpl();
        List<ParkingLot> s1 = new ArrayList<ParkingLot>();
        List<ParkingLot> s2 = new ArrayList<ParkingLot>();
        final int INDEX_PAGE = Pager.getIndexPage(request.getParameter("id"));

        try {
            s1 = parkingLotDAO.SearchParking(search, id);
            s2 = parkingLotDAO.SearchPaging(INDEX_PAGE, id, search);
            if (s2.isEmpty()) {
                s2 = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final int TOTAL = s1.size();
        final int END_PAGE = Pager.getEndPage(TOTAL);

        int issearch = 1;
        request.setAttribute("bulen", issearch);
        request.setAttribute("currentPage", INDEX_PAGE);
        request.setAttribute("numberPage", END_PAGE);
        request.setAttribute("listP", s2);
        request.setAttribute("filter", id);
        request.setAttribute("keySearch", search);
        request.getRequestDispatcher("parkinglot/ListParkingLot.jsp?").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Employee employee = Pager.getEmployeeFromSession(request, response);
        if (!"CPO".equalsIgnoreCase(employee.getDepartment())) {
            response.sendRedirect("common/block.jsp");
        } else {
            String search = request.getParameter("search");
            int id = Integer.parseInt(request.getParameter("filter"));
            System.out.println(id);
            request.getSession().setAttribute("search2", id);
            request.getSession().setAttribute("search1", search);
            doGet(request, response);
        }
    }
}
