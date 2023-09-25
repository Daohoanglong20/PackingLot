package dao;


import model.ParkingLot;

import java.sql.SQLException;
import java.util.List;

public interface ParkingLotDAO {

    ParkingLot getParkingLotById(int id) throws SQLException;

    List<ParkingLot> GetAllParkingLot() throws SQLException;

    List<ParkingLot> getParkingPaging(int x) throws SQLException;

    int AddParkingLot(ParkingLot p) throws SQLException;

    void deleteParkingLot(int id) throws SQLException;

    void updateParkingLot(ParkingLot p) throws SQLException;

    List<ParkingLot> SearchParking(String search, int id) throws SQLException;

    List<ParkingLot> SearchPaging(int x, int id, String search) throws SQLException;
}
