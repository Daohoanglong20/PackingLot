package dao.Impl;


import common.SQLConnection;
import dao.EmployeeDAO;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection Connection;
    private PreparedStatement pre;
    private ResultSet ResultSet;

    @Override
    public List<String> GetAllDepartment() throws SQLException {
        List<String> a = new ArrayList<String>();
        String sql = "SELECT * FROM [CarPark].[dbo].[department] ";
        try {
            Connection = SQLConnection.getConnection();
            pre = Connection.prepareStatement(sql);
            ResultSet = pre.executeQuery();
            while (ResultSet.next()) {
                a.add(ResultSet.getString("departmentName"));

            }
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }

        }
        return a;
    }

    @Override
    public List<Employee> GetAllEmployees() throws SQLException {
        List<Employee> a = new ArrayList<Employee>();
        String sql = "select * from employee ";
        try {
            Connection = SQLConnection.getConnection();
            pre = Connection.prepareStatement(sql);
            ResultSet = pre.executeQuery();
            while (ResultSet.next()) {
                Employee emp = new Employee();
                emp.setEID(ResultSet.getInt(1));
                emp.setAccount(ResultSet.getString(2));
                emp.setDepartment(ResultSet.getString(3));
                emp.setAddress(ResultSet.getString(4));
                emp.setBirthdate(ResultSet.getString(5));
                emp.setEmail(ResultSet.getString(6));
                emp.setName(ResultSet.getString(7));
                emp.setPhone(ResultSet.getString(8));
                emp.setSex(ResultSet.getBoolean(9));
                emp.setPassword(ResultSet.getString("password"));
                a.add(emp);

            }
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }

        }
        return a;
    }

    @Override
    public List<Employee> GetEmployeesTheoTrang(int x) throws SQLException {
        List<Employee> a = new ArrayList<Employee>();

        String sql = "select * from (select ROW_NUMBER() over (order by employeeId asc) as r,* from employee) as x where r between ?*3-2 and ?*3";

        try {
            Connection = SQLConnection.getConnection();
            pre = Connection.prepareStatement(sql);
            pre.setInt(1, x);
            pre.setInt(2, x);
            ResultSet = pre.executeQuery();
            while (ResultSet.next()) {
                Employee emp = new Employee();
                emp.setEID(ResultSet.getInt(2));
                emp.setAccount(ResultSet.getString(3));
                emp.setDepartment(ResultSet.getString(4));
                emp.setAddress(ResultSet.getString(5));
                emp.setBirthdate(ResultSet.getString(6));
                emp.setEmail(ResultSet.getString(7));
                emp.setName(ResultSet.getString(8));
                emp.setPhone(ResultSet.getString(9));
                emp.setSex(ResultSet.getBoolean(10));
                emp.setPassword(ResultSet.getString("password"));
                a.add(emp);

            }
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }

        }
        return a;
    }

    @Override
    public int staffAddEmployee(Employee e) throws SQLException {
        int n = 0;
        try {
            Connection = SQLConnection.getConnection();
            pre = Connection.prepareStatement("INSERT INTO employee(account,department,employeeAddress,employeeBirthdate,employeeEmail,employeeName,employeePhone,sex,[password]) values (?,?,?,?,?,?,?,?,?)");
            pre.setString(1, e.getAccount());
            pre.setString(2, e.getDepartment());
            pre.setString(3, e.getAddress());
            pre.setString(4, e.getBirthdate());
            pre.setString(5, e.getEmail());
            pre.setString(6, e.getName());
            pre.setString(7, e.getPhone());
            pre.setBoolean(8, e.isSex());
            pre.setString(9, e.getPassword());

            n = pre.executeUpdate();


        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }

        }
        return n;
    }

    @Override
    public void delete(int id) throws SQLException {
        int n = 0;

        try {
            Connection = SQLConnection.getConnection();
            pre = Connection.prepareStatement("delete from employee where employeeId = ?");
            pre.setInt(1, id);
            pre.executeUpdate();

        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }

        }
    }

    @Override
    public List<Employee> SearchEmployee(int x, int id, String search) throws SQLException {
        List<Employee> a = new ArrayList<Employee>();
        String sql = "";
        if (id == 1) {
            sql = "select * from (select ROW_NUMBER() over (order by employeeId asc) as r,* from employee where employeeName like ?) as x where r between ?*3-2 and ?*3";
        } else if (id == 2) {
            sql = "select * from (select ROW_NUMBER() over (order by employeeId asc) as r,* from employee where department like ?) as x where r between ?*3-2 and ?*3";
        } else {
            sql = "select * from (select ROW_NUMBER() over (order by employeeId asc) as r,* from employee where employeePhone like ?) as x where r between ?*3-2 and ?*3";
        }
        try {
            Connection = SQLConnection.getConnection();
            pre = Connection.prepareStatement(sql);
            pre.setString(1, "%" + search + "%");
            pre.setInt(2, x);
            pre.setInt(3, x);
            ResultSet = pre.executeQuery();
            while (ResultSet.next()) {
                Employee emp = new Employee();
                emp.setEID(ResultSet.getInt(2));
                emp.setAccount(ResultSet.getString(3));
                emp.setDepartment(ResultSet.getString(4));
                emp.setAddress(ResultSet.getString(5));
                emp.setBirthdate(ResultSet.getString(6));
                emp.setEmail(ResultSet.getString(7));
                emp.setName(ResultSet.getString(8));
                emp.setPhone(ResultSet.getString(9));
                emp.setSex(ResultSet.getBoolean(10));
                emp.setPassword(ResultSet.getString("password"));
                a.add(emp);

            }
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return a;
    }

    @Override
    public List<Employee> SearchEmployeessssssss(String search, int id) throws SQLException {
        List<Employee> a = new ArrayList<Employee>();
        String sql = "";
        if (id == 1) {
            sql = "select * from employee where employeeName like ?";
        } else if (id == 2) {
            sql = "select * from employee where department like ?";
        } else {
            sql = "select * from employee where employeePhone like ?";
        }
        try {
            Connection = SQLConnection.getConnection();

            pre = Connection.prepareStatement(sql);
            pre.setString(1, "%" + search + "%");
            ResultSet = pre.executeQuery();
            while (ResultSet.next()) {
                Employee emp = new Employee();
                emp.setEID(ResultSet.getInt(1));
                emp.setAccount(ResultSet.getString(2));
                emp.setDepartment(ResultSet.getString(3));
                emp.setAddress(ResultSet.getString(4));
                emp.setBirthdate(ResultSet.getString(5));
                emp.setEmail(ResultSet.getString(6));
                emp.setName(ResultSet.getString(7));
                emp.setPhone(ResultSet.getString(8));
                emp.setSex(ResultSet.getBoolean(9));
                a.add(emp);
            }
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }

        }
        return a;
    }

    @Override
    public Employee GetEmployees(int x) throws SQLException {
        Employee emp = new Employee();
        String sql = "select * from employee where employeeId = ?";

        try {
            Connection = SQLConnection.getConnection();

            pre = Connection.prepareStatement(sql);
            pre.setInt(1, x);
            ResultSet = pre.executeQuery();
            while (ResultSet.next()) {

                emp.setEID(ResultSet.getInt(1));
                emp.setAccount(ResultSet.getString(2));
                emp.setDepartment(ResultSet.getString(3));
                emp.setAddress(ResultSet.getString(4));
                emp.setBirthdate(ResultSet.getString(5));
                emp.setEmail(ResultSet.getString(6));
                emp.setName(ResultSet.getString(7));
                emp.setPhone(ResultSet.getString(8));
                emp.setSex(ResultSet.getBoolean(9));
                emp.setPassword(ResultSet.getString("password"));
            }
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }

        }

        return emp;
    }

    @Override
    public void update(Employee e) throws SQLException {
        String sql = "UPDATE [dbo].[employee] SET [account] = ? ,[department] = ? ,[employeeAddress] = ? ,[employeeBirthdate] = ?  ,[employeeEmail] = ? ,[employeeName] = ? ,[employeePhone] = ?,[sex] = ?, [password] =? WHERE employeeId = ? ";
        int n = 0;
        try {
            Connection = SQLConnection.getConnection();
            pre = Connection.prepareStatement(sql);
            pre.setString(1, e.getAccount());
            pre.setString(2, e.getDepartment());
            pre.setString(3, e.getAddress());
            pre.setString(4, e.getBirthdate());
            pre.setString(5, e.getEmail());
            pre.setString(6, e.getName());
            pre.setString(7, e.getPhone());
            pre.setBoolean(8, e.isSex());
            pre.setInt(10, e.getEID());
            pre.setString(9, e.getPassword());
            n = pre.executeUpdate();

        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }

    @Override
    public Employee GetEmployeeByAccount(String account) throws SQLException {
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE account = ?";

        try {
            Connection = SQLConnection.getConnection();

            pre = Connection.prepareStatement(sql);
            pre.setString(1, account);
            ResultSet = pre.executeQuery();
            while (ResultSet.next()) {
                Employee emp = new Employee();
                emp.setEID(ResultSet.getInt(1));
                emp.setAccount(ResultSet.getString(2));
                emp.setDepartment(ResultSet.getString(3));
                emp.setAddress(ResultSet.getString(4));
                emp.setBirthdate(ResultSet.getString(5));
                emp.setEmail(ResultSet.getString(6));
                emp.setName(ResultSet.getString(7));
                emp.setPhone(ResultSet.getString(8));
                emp.setSex(ResultSet.getBoolean(9));
                emp.setPassword(ResultSet.getString("password"));
                employee = emp;
            }
        } finally {
            if (ResultSet != null) {
                ResultSet.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
        return employee;
    }

    @Override
    public Employee Login(String account, String password) {
        String sql = "SELECT * FROM employee WHERE account = ? and password=?";

        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement PreparedStatement = connection.prepareStatement(sql)) {
            PreparedStatement.setString(1, account);
            PreparedStatement.setString(2, password);
            ResultSet ResultSet = PreparedStatement.executeQuery();
            while (ResultSet.next()) {
                Employee employee = new Employee();
                employee.setEID(ResultSet.getInt(1));
                employee.setAccount(ResultSet.getString(2));
                employee.setDepartment(ResultSet.getString(3));
                employee.setAddress(ResultSet.getString(4));
                employee.setBirthdate(ResultSet.getString(5));
                employee.setEmail(ResultSet.getString(6));
                employee.setName(ResultSet.getString(7));
                employee.setPhone(ResultSet.getString(8));
                employee.setPassword(ResultSet.getString(9));
                employee.setSex(ResultSet.getBoolean(10));
                return employee;
            }
        } catch (Exception e) {
            System.out.println("Get Employee Info Fail: " + e.getMessage());
        }
        return null;
    }
}
