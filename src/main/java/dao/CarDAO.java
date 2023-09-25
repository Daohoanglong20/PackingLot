package dao;

import model.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    Car getCarByLicensePlate(String licensePlate) throws SQLException;

    List<Car> getAllCar() throws SQLException;

    List<Car> getAllCarBySearch(String keySearch, String filter) throws SQLException;

    List<Car> getCarPaging(int indexPage) throws SQLException;

    List<Car> getCarPagingBySearch(String keySearch, String filter, int indexPage) throws SQLException;

    boolean add(Car car) throws SQLException;

    boolean delete(String licensePlate) throws SQLException;

    boolean update(Car car) throws SQLException;

}
