package dao.Impl;

import common.Pager;
import common.SQLConnection;
import dao.CarDAO;
import dao.TicketDAO;
import model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static String getSQLCommandSearch(String filter) {
        String sqlCommand = "";
        switch (filter) {
            case "licensePlate":
                sqlCommand = "SELECT * FROM car WHERE licensePlate LIKE ?";
                break;
            case "carColor":
                sqlCommand = "SELECT * FROM car WHERE carColor LIKE ?";
                break;
            case "carType":
                sqlCommand = "SELECT * FROM car WHERE carType LIKE ?";
                break;
            case "company":
                sqlCommand = "SELECT * FROM car WHERE company LIKE ?";
                break;
        }
        return sqlCommand;
    }

    public static String getSQLCommandPaging(String filter) {
        String sqlCommand = "";
        switch (filter) {
            case "licensePlate":
                sqlCommand = "SELECT * FROM car WHERE licensePlate LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT " + Pager.CONTENT_PER_PAGE + " ROWS ONLY";
                break;
            case "carColor":
                sqlCommand = "SELECT * FROM car WHERE carColor LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT " + Pager.CONTENT_PER_PAGE + " ROWS ONLY";
                break;
            case "carType":
                sqlCommand = "SELECT * FROM car WHERE carType LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT " + Pager.CONTENT_PER_PAGE + " ROWS ONLY";
                break;
            case "company":
                sqlCommand = "SELECT * FROM car WHERE company LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT " + Pager.CONTENT_PER_PAGE + " ROWS ONLY";
                break;
        }
        return sqlCommand;
    }

    @Override
    public Car getCarByLicensePlate(String licensePlate) throws SQLException {
        Car car = null;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Car WHERE licensePlate = ? ");
            preparedStatement.setString(1, licensePlate);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String carColor = resultSet.getString("carColor");
                String carType = resultSet.getString("carType");
                String company = resultSet.getString("company");
                int parkId = resultSet.getInt("parkId");
                car = new Car(licensePlate, carColor, carType, company, parkId);
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
        return car;
    }

    @Override
    public List<Car> getAllCar() throws SQLException {
        List<Car> carList = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Car");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setLincensePlate(resultSet.getString("licensePlate"));
                car.setCarColor(resultSet.getString("carColor"));
                car.setCarType(resultSet.getString("carType"));
                car.setCompany(resultSet.getString("company"));
                car.setParkId(resultSet.getInt("parkId"));
                carList.add(car);
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
        return carList;
    }

    @Override
    public List<Car> getAllCarBySearch(String keySearch, String filter) throws SQLException {
        List<Car> carList = new ArrayList<>();
        String sqlCommandSearch = getSQLCommandSearch(filter);
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlCommandSearch);
            preparedStatement.setString(1, "%" + keySearch + "%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setLincensePlate(resultSet.getString("licensePlate"));
                car.setCarColor(resultSet.getString("carColor"));
                car.setCarType(resultSet.getString("carType"));
                car.setCompany(resultSet.getString("company"));
                car.setParkId(resultSet.getInt("parkId"));
                carList.add(car);
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
        return carList;
    }

    @Override
    public List<Car> getCarPaging(int indexPage) throws SQLException {
        List<Car> carList = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM car ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT " + Pager.CONTENT_PER_PAGE + " ROWS ONLY");
            preparedStatement.setInt(1, (indexPage - 1) * Pager.CONTENT_PER_PAGE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setLincensePlate(resultSet.getString("licensePlate"));
                car.setCarColor(resultSet.getString("carColor"));
                car.setCarType(resultSet.getString("carType"));
                car.setCompany(resultSet.getString("company"));
                car.setParkId(resultSet.getInt("parkId"));
                carList.add(car);
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
        return carList;
    }

    @Override
    public List<Car> getCarPagingBySearch(String keySearch, String filter, int indexPage) throws SQLException {
        List<Car> carList = new ArrayList<>();
        String sqlCommandPaging = getSQLCommandPaging(filter);
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlCommandPaging);
            preparedStatement.setString(1, "%" + keySearch + "%");
            preparedStatement.setInt(2, (indexPage - 1) * Pager.CONTENT_PER_PAGE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setLincensePlate(resultSet.getString("licensePlate"));
                car.setCarColor(resultSet.getString("carColor"));
                car.setCarType(resultSet.getString("carType"));
                car.setCompany(resultSet.getString("company"));
                car.setParkId(resultSet.getInt("parkId"));
                carList.add(car);
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
        return carList;
    }

    @Override
    public boolean add(Car car) throws SQLException {
        int row = 0;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Car VALUES(?,?, ?,?, ?)");
            preparedStatement.setString(1, car.getLincensePlate());
            preparedStatement.setString(2, car.getCarColor());
            preparedStatement.setString(3, car.getCarType());
            preparedStatement.setString(4, car.getCompany());
            preparedStatement.setInt(5, car.getParkId());
            row = preparedStatement.executeUpdate();
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
        return row > 0;
    }

    @Override
    public boolean delete(String licensePlate) throws SQLException {
        int row = 0;
        TicketDAO ticketDAO = new TicketDAOImpl();
        ticketDAO.deleteTicketByLicenseCar(licensePlate);
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM car WHERE licensePlate = ?");
            preparedStatement.setString(1, licensePlate);
            row = preparedStatement.executeUpdate();
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
        return row > 0;
    }

    @Override
    public boolean update(Car car) throws SQLException {
        int row = 0;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE car SET carColor =?, carType =?, company = ?, parkId = ? WHERE licensePlate = ?");
            preparedStatement.setString(1, car.getCarColor());
            preparedStatement.setString(2, car.getCarType());
            preparedStatement.setString(3, car.getCompany());
            preparedStatement.setInt(4, car.getParkId());
            preparedStatement.setString(5, car.getLincensePlate());

            row = preparedStatement.executeUpdate();
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
        return row > 0;
    }
}
