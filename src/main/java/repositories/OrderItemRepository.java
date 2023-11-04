package repositories;

import entities.Order;
import entities.OrderItem;
import scripts.database.ConnectionCloser;
import scripts.database.DbConnection;
import scripts.interfaces.CRUD;
import scripts.mappers.DbOrderItemMapper;
import scripts.mappers.DbOrderMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static scripts.constants.OrderItemQueriesConstants.*;

public class OrderItemRepository implements CRUD<OrderItem> {
    private ConnectionCloser connectionCloser;
    private DbConnection connection;

    public OrderItemRepository(DbConnection connection) {
        this.connection = connection;

        connectionCloser = new ConnectionCloser();
    }

    @Override
    public void create(OrderItem orderItem) {
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(INSERT_QUERY);
            pullPreparedStatement(pstmt, orderItem);

            int insert_rows = pstmt.executeUpdate();

            System.out.println("OrderItemRepository -> created "+insert_rows+" orderItem(s)");
        } catch (SQLException ex){
            throw new RuntimeException("Create orderItem error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    @Override
    public List<OrderItem> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.getOpenConnection().prepareStatement(FIND_ALL_QUERY);
            rs = pstmt.executeQuery();

            List<OrderItem> orderItems = new DbOrderItemMapper().mapAll(rs);

            System.out.println("OrderItemRepository -> found "+ orderItems.size()+" orderItem(s)");

            return orderItems;
        } catch (SQLException ex){
            throw new RuntimeException("FindAll orderItem error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    @Override
    public OrderItem findById(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(FIND_BY_ID_QUERY);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            OrderItem orderItem = new DbOrderItemMapper().mapFirst(rs);

            System.out.println("OrderItemRepository -> found " + orderItem);

            return orderItem;
        } catch(SQLException ex){
            throw new RuntimeException("FindById orderItem error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    @Override
    public void update(OrderItem orderItem) {
        PreparedStatement pstmt = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(UPDATE_QUERY);
            pullPreparedStatement(pstmt, orderItem);
            pstmt.setInt(5, orderItem.getOrderId());

            int updatedRows = pstmt.executeUpdate();

            System.out.println("OrderItemRepository -> updated "+ updatedRows + " orderItem(s)");
        } catch(SQLException ex){
            throw new RuntimeException("Update orderItem error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);

            int deletedRows = pstmt.executeUpdate();

            System.out.println("OrderItemRepository -> deleted " + deletedRows + " orderItem(s)" );
        } catch(SQLException ex){
            throw new RuntimeException("Delete orderItem error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    private void pullPreparedStatement(PreparedStatement pstmt, OrderItem orderItem) throws SQLException {
        pstmt.setInt(1, orderItem.getOrderId());
        pstmt.setInt(2, orderItem.getProductId());
        pstmt.setInt(3, orderItem.getQuantity());
        pstmt.setDouble(4, orderItem.getUnitPrice());
    }
}
