package repositories;

import entities.Shipper;
import scripts.database.ConnectionCloser;
import scripts.database.DbConnection;
import scripts.interfaces.CRUD;
import scripts.interfaces.DbMapper;
import scripts.mappers.DbShipperMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static scripts.constants.ShipperQueriesConstants.*;

public class ShipperRepository implements CRUD<Shipper, Integer> {
    private DbConnection dbConnection;
    private ConnectionCloser connectionCloser;
    private DbMapper<Shipper> mapper;

    public ShipperRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;

        this.connectionCloser = new ConnectionCloser();
        mapper = new DbShipperMapper();
    }

    public void create(Shipper entity){
        PreparedStatement pstmt = null;

        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(INSERT_QUERY);
            pstmt.setString(1, entity.getName());

            int insertedRows = pstmt.executeUpdate();

            System.out.println("ShipperRepository -> saved " + insertedRows + " shipper(s)");
        } catch(SQLException ex){
            throw new RuntimeException("Create shipper error", ex);
        } finally {
            connectionCloser.close(dbConnection, pstmt);
        }
    }

    public List<Shipper> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(FIND_ALL_QUERY);
            rs = pstmt.executeQuery();

            List<Shipper> shippers = mapper.mapAll(rs);

            System.out.println("ShipperRepository -> found " + shippers.size() + " shippers");

            return shippers;
        } catch(SQLException ex){
            throw new RuntimeException("Read shippers error", ex);
        } finally {
            connectionCloser.close(dbConnection, rs, pstmt);
        }
    }

    public Shipper findById(Integer id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(FIND_BY_ID_QUERY);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            Shipper shipper = mapper.mapFirst(rs);

            System.out.println("ShipperRepository -> found "+ shipper);

            return shipper;
        } catch(SQLException ex){
            throw new RuntimeException("Find by id shipper error", ex);
        } finally {
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
        } catch(SQLException ex){
            throw new RuntimeException("Update shipper error", ex);
        } finally {
            connectionCloser.close(dbConnection, pstmt);
        }
    }

    public void delete(Integer id) {
        PreparedStatement pstmt = null;

        try{
            pstmt = dbConnection.getOpenConnection().prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);

            int deletedRows = pstmt.executeUpdate();

            System.out.println("ShipperRepository -> deleted " + deletedRows + " shipper(s)" );
        } catch(SQLException ex){
            throw new RuntimeException("Delete shipper error", ex);
        } finally {
            connectionCloser.close(dbConnection, pstmt);
        }
    }
}
