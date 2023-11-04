package Repositories;

import Entities.Shipper;
import Scripts.Database.ConnectionCloser;
import Scripts.Database.DbConnection;
import Scripts.Interfaces.ICRUD;
import Scripts.Mappers.DbShipperMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static Scripts.Constants.ShipperQueriesConstants.*;

public class ShipperRepository implements ICRUD<Shipper> {
    private DbConnection dbConnection;
    private ConnectionCloser connectionCloser;

    public ShipperRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.connectionCloser = new ConnectionCloser();
    }

    public void create(Shipper entity){
        PreparedStatement pstmt = null;

        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(INSERT_QUERY);
            pstmt.setString(1, entity.getName());

            int savedRow = pstmt.executeUpdate();

            System.out.println("ShipperRepository -> saved " + savedRow + " shipper(s)");
        }
        catch(SQLException ex){
            throw new RuntimeException("Create shipper error", ex);
        }
        finally {
            connectionCloser.close(dbConnection, pstmt);
        }
    }

    public List<Shipper> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(FIND_ALL_QUERY);
            rs = pstmt.executeQuery();

            List<Shipper> shippers = new DbShipperMapper().mapAll(rs);

            System.out.println("ShipperRepository -> found " + shippers.size() + " shippers");

            return shippers;
        }
        catch(SQLException ex){
            throw new RuntimeException("Read shippers error", ex);
        }
        finally {
            connectionCloser.close(dbConnection, rs, pstmt);
        }
    }

    public Shipper findById(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(FIND_BY_ID_QUERY);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            Shipper shipper = new DbShipperMapper().mapFirst(rs);

            System.out.println("ShipperRepository -> found "+ shipper);

            return shipper;
        }
        catch(SQLException ex){
            throw new RuntimeException("Find by id shipper error", ex);
        }
        finally {
            connectionCloser.close(dbConnection, rs, pstmt);
        }

    }

    public void update(Shipper entity){
        PreparedStatement pstmt = null;
        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(UPDATE_QUERY);
            pstmt.setString(1, entity.getName());
            pstmt.setInt(2, entity.getShipperId());

            int updatedRows = pstmt.executeUpdate();

            System.out.println("Updated "+ updatedRows + " shipper(s)");
        }
        catch(SQLException ex){
            throw new RuntimeException("Update shipper error", ex);
        }
        finally {
            connectionCloser.close(dbConnection, pstmt);
        }

    }

    public void delete(int id) {
        PreparedStatement pstmt = null;

        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);

            int deletedRows = pstmt.executeUpdate();

            System.out.println("ShipperRepository -> deleted " + deletedRows + " shipper(s)" );
        }
        catch(SQLException ex){
            throw new RuntimeException("Delete shipper error", ex);
        }
        finally {
            connectionCloser.close(dbConnection, pstmt);
        }

    }
}