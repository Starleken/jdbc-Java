package repositories;

import entities.OrderStatus;
import scripts.constants.OrderStatusQueriesConstants;
import scripts.database.ConnectionCloser;
import scripts.database.DbConnection;
import scripts.interfaces.CRUD;
import scripts.mappers.DbOrderStatusMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static scripts.constants.ShipperQueriesConstants.*;

public class OrderStatusRepository implements CRUD<OrderStatus, Integer> {
    private ConnectionCloser closer;
    private DbConnection connection;

    public OrderStatusRepository(DbConnection connection) {
        this.connection = connection;

        closer = new ConnectionCloser();
    }

    public void create(OrderStatus orderStatus) {
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, orderStatus.getId());
            pstmt.setString(2, orderStatus.getName());

            int insert_rows = pstmt.executeUpdate();

            System.out.println("OrderStatusRepository -> created "+insert_rows+" orderstatus(es)");
        } catch (SQLException ex){
            throw new RuntimeException("Create OrderStatus error", ex);
        } finally {
            closer.close(connection, pstmt);
        }
    }

    public List<OrderStatus> findAll(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.getOpenConnection().prepareStatement(FIND_ALL_QUERY);
            rs = pstmt.executeQuery();

            List<OrderStatus> orderStatuses = new DbOrderStatusMapper().mapAll(rs);

            System.out.println("OrderStatusRepository -> found "+orderStatuses.size()+" OrderStatus(es)");

            return orderStatuses;
        } catch (SQLException ex){
            throw new RuntimeException("FindAll OrderStatus error", ex);
        } finally {
            closer.close(connection, rs, pstmt);
        }
    }

    public OrderStatus findById(Integer id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(FIND_BY_ID_QUERY);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            OrderStatus orderStatus = new DbOrderStatusMapper().mapFirst(rs);

            System.out.println("OrderStatus -> Found "+orderStatus.toString()+"");

            return orderStatus;
        } catch(SQLException ex){
            throw new RuntimeException("FindById OrderStatus error", ex);
        } finally {
            closer.close(connection, rs, pstmt);
        }
    }

    public void update(OrderStatus orderStatus){
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(UPDATE_QUERY);
            pstmt.setString(1, orderStatus.getName());
            pstmt.setInt(2, orderStatus.getId());
            int updatedRows = pstmt.executeUpdate();

            System.out.println("OrderStatusRepository -> updated "+updatedRows+" row(s)");
        } catch(SQLException ex){
            throw new RuntimeException("Update OrderStatus error", ex);
        } finally {
            closer.close(connection, pstmt);
        }
    }

    public void delete(Integer id){
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);

            int deletedRows = pstmt.executeUpdate();
            System.out.println("OrderStatusRepository -> deleted "+deletedRows+" row(s)");
        } catch(SQLException ex){
            throw new RuntimeException("Delete OrderStatus error", ex);
        } finally {
            closer.close(connection, pstmt);
        }
    }
}
