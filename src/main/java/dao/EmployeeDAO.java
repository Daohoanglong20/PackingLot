package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    List<String> GetAllDepartment() throws SQLException;

    List<Employee> GetAllEmployees() throws SQLException;

    List<Employee> GetEmployeesTheoTrang(int x) throws SQLException;

    int staffAddEmployee(Employee e) throws SQLException;

    void delete(int id) throws SQLException;

    List<Employee> SearchEmployee(int x, int id, String search) throws SQLException;

    List<Employee> SearchEmployeessssssss(String search, int id) throws SQLException;

    Employee GetEmployees(int x) throws SQLException;

    void update(Employee e) throws SQLException;

    Employee GetEmployeeByAccount(String account) throws SQLException;

    Employee Login(String account, String password) throws SQLException;
}
