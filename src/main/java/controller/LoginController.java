package controller;

import Utils.SessionUtils;
import dao.EmployeeDAO;
import dao.Impl.EmployeeDAOImpl;
import lombok.SneakyThrows;
import model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = "/Login", name = "LoginController")
public class LoginController extends HttpServlet {
    Locale locale = new Locale("en");
    ResourceBundle resourceBundle = ResourceBundle.getBundle("mess", locale);

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("Login")) {
            String remember = req.getParameter("remember");
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            Employee employee = employeeDAO.Login(account, password);
            if (employee != null) {
                SessionUtils.getInstance().putValue(req, "employee", employee);
                Cookie cEmail = new Cookie("account", account);
                Cookie cPassword = new Cookie("password", password);
                Cookie cRemember = new Cookie("remember", remember);
                if (remember != null) {
                    cEmail.setMaxAge(60 * 60);
                    cPassword.setMaxAge(60 * 60);
                    cRemember.setMaxAge(60 * 60);
                } else {
                    cEmail.setMaxAge(0);
                    cPassword.setMaxAge(0);
                    cRemember.setMaxAge(0);
                }
                resp.addCookie(cEmail);
                resp.addCookie(cPassword);
                resp.addCookie(cRemember);

                if (employee.getDepartment().equals("HRM")) {
                    resp.sendRedirect(req.getContextPath() + "/staffEmployeeList");
                } else if (employee.getDepartment().equals("CPO")) {
                    resp.sendRedirect(req.getContextPath() + "/ListCar");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/triplist");
                }

            } else {
                resp.sendRedirect(req.getContextPath() + "/Login?action=Login&message=username_password_invalid&alert=danger");
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String e = null;
        String p = null;
        String r = null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().trim().equals("account")) {
                e = cookies[i].getValue();
            }
            if (cookies[i].getName().trim().equals("password")) {
                p = cookies[i].getValue();
            }
            if (cookies[i].getName().trim().equals("remember")) {
                r = cookies[i].getValue();
            }
        }
        req.setAttribute("account", e);
        req.setAttribute("password", p);
        req.setAttribute("remember", r);

        req.getRequestDispatcher("Login.jsp").forward(req, resp);


        String action = req.getParameter("action");
        if (action != null && action.equals("Login")) {
            String alert = req.getParameter("alert");
            String message = req.getParameter("message");
            if (message != null && alert != null) {
                req.setAttribute("message", resourceBundle.getString(message));
                req.setAttribute("alert", alert);
            }
            RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
            rd.forward(req, resp);
        }
    }
}
