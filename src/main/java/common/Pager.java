package common;

import model.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Pager {
    public static final int CONTENT_PER_PAGE = 3;

    public static int getIndexPage(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            i = 1;
        }
        return i;
    }

    public static int getEndPage(int total) {
        int endPage = total / CONTENT_PER_PAGE;
        if (total % CONTENT_PER_PAGE != 0) {
            endPage++;
        }
        return endPage;
    }

    public static Employee getEmployeeFromSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        return employee;
    }
}
