package dao.Impl;

import common.SQLConnection;
import dao.ParkingLotDAO;
import model.ParkingLot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotDAOImpl implements ParkingLotDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public ParkingLot getParkingLotById(int parkId) throws SQLException {
        ParkingLot parkingLot = null;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM parkinglot WHERE parkId = ?");
            preparedStatement.setInt(1, parkId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int area = resultSet.getInt("parkArea");
                String name = resultSet.getString("parkName");
                String place = resultSet.getString("parkPlace");
                double price = resultSet.getDouble("parkPrice");
                String status = resultSet.getString("parkStatus");
                parkingLot = new ParkingLot(parkId, area, name, place, price, status);
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
        return parkingLot;
    }

    @Override
    public List<ParkingLot> GetAllParkingLot() throws SQLException {
        List<ParkingLot> listP = new ArrayList<ParkingLot>();
        String sql = "SELECT * FROM parkinglot";
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ParkingLot list = new ParkingLot();
                list.setParkId(resultSet.getInt(1));
                list.setParkArea(resultSet.getInt(2));
                list.setParkName(resultSet.getString(3));
                list.setParkPlace(resultSet.getString(4));
                list.setParkPrice(resultSet.getDouble(5));
                list.setParkStatus(resultSet.getString(6));
                listP.add(list);
            }
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
        return listP;
    }

    @Override
    public List<ParkingLot> getParkingPaging(int x) throws SQLException {
        List<ParkingLot> listP = new ArrayList<ParkingLot>();
        String sql = "select * from (select ROW_NUMBER() over (order by parkId asc) as r,* from dbo.parkinglot) as x where r between ?*3-2 and ?*3";
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, x);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ParkingLot list = new ParkingLot();
                list.setParkId(resultSet.getInt(2));
                list.setParkArea(resultSet.getInt(3));
                list.setParkName(resultSet.getString(4));
                list.setParkPlace(resultSet.getString(5));
                list.setParkPrice(resultSet.getDouble(6));
                list.setParkStatus(resultSet.getString(7));
                listP.add(list);
            }
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
        return listP;
    }

    @Override
    public int AddParkingLot(ParkingLot p) throws SQLException {
        String sql = "INSERT INTO dbo.parkinglot (parkArea,parkName,parkPlace,parkPrice,parkStatus)  VALUES (?,?,?,?,?)";
        int n = 0;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, p.getParkArea());
            preparedStatement.setString(2, p.getParkName());
            preparedStatement.setString(3, p.getParkPlace());
            preparedStatement.setDouble(4, p.getParkPrice());
            preparedStatement.setString(5, p.getParkStatus());
            n = preparedStatement.executeUpdate();
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
        return n;
    }

    @Override
    public void deleteParkingLot(int id) throws SQLException {
        String sql = "DELETE FROM dbo.parkinglot WHERE parkId = ?";
        int n = 0;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

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
    }

    @Override
    public void updateParkingLot(ParkingLot p) throws SQLException {
        String sql = "UPDATE dbo.parkinglot SET parkName = ?, parkPlace = ?, parkArea = ?, parkPrice = ?,parkStatus = ? WHERE parkId = ?";
        int n = 0;
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, p.getParkName());
            preparedStatement.setString(2, p.getParkPlace());
            preparedStatement.setInt(3, p.getParkArea());
            preparedStatement.setDouble(4, p.getParkPrice());
            preparedStatement.setString(5, p.getParkStatus());
            preparedStatement.setInt(6, p.getParkId());
            preparedStatement.executeUpdate();

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
    }

    @Override
    public List<ParkingLot> SearchParking(String search, int id) throws SQLException {
        List<ParkingLot> p = new ArrayList<ParkingLot>();
        String sql = "";
        if (id == 1) {
            sql = "select * from dbo.parkinglot where parkPlace like ?";
        } else {
            sql = "select * from dbo.parkinglot where parkStatus like ?";
        }
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + search + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ParkingLot list = new ParkingLot();
                list.setParkId(resultSet.getInt(1));
                list.setParkArea(resultSet.getInt(2));
                list.setParkName(resultSet.getString(3));
                list.setParkPlace(resultSet.getString(4));
                list.setParkPrice(resultSet.getDouble(5));
                list.setParkStatus(resultSet.getString(6));
                p.add(list);
            }
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
        return p;
    }

    @Override
    public List<ParkingLot> SearchPaging(int x, int id, String search) throws SQLException {
        List<ParkingLot> p = new ArrayList<ParkingLot>();
        String sql = "";
        if (id == 1) {
            sql = "select * from (select ROW_NUMBER() over (order by parkId asc) as r,* from dbo.parkinglot where parkPlace like ?) as x where r between ?*3-2 and ?*3";
        } else {
            sql = "select * from (select ROW_NUMBER() over (order by parkId asc) as r,* from dbo.parkinglot where parkStatus like ?) as x where r between ?*3-2 and ?*3";
        }
        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setInt(2, x);
            preparedStatement.setInt(3, x);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ParkingLot list = new ParkingLot();
                list.setParkId(resultSet.getInt(2));
                list.setParkArea(resultSet.getInt(3));
                list.setParkName(resultSet.getString(4));
                list.setParkPlace(resultSet.getString(5));
                list.setParkPrice(resultSet.getDouble(6));
                list.setParkStatus(resultSet.getString(7));
                p.add(list);
            }
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

        return p;
    }
}
