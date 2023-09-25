package dao;

import common.SQLConnection;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Employee getEmployeeByAccount(String account) throws SQLException {
        Employee employee = null;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE account = ?");
            preparedStatement.setString(1, account);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee emp = new Employee();
                emp.setEID(resultSet.getInt("employeeId"));
                emp.setAccount(resultSet.getString("account"));
                emp.setDepartment(resultSet.getString("department"));
                employee = emp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employee;
    }

    public Employee getEmployeeByAccountAndPassword(String account, String password) throws SQLException {
        Employee employee = null;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE account = ?  AND password = ?");
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee emp = new Employee();
                emp.setEID(resultSet.getInt("employeeId"));
                emp.setAccount(resultSet.getString("account"));
                emp.setDepartment(resultSet.getString("department"));
                emp.setAddress(resultSet.getString("employeeAddress"));
                emp.setBirthdate(resultSet.getString("employeeBirthdate"));
                emp.setEmail(resultSet.getString("employeeEmail"));
                emp.setName(resultSet.getString("employeeName"));
                emp.setPhone(resultSet.getString("employeePhone"));
                emp.setSex(resultSet.getBoolean("sex"));
                emp.setPassword(resultSet.getString("password"));
                employee = emp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employee;
    }
}
