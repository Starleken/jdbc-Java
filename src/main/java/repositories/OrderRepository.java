package repositories;

import entities.Customer;
import entities.Order;
import scripts.database.ConnectionCloser;
import scripts.database.DbConnection;
import scripts.interfaces.CRUD;
import scripts.interfaces.DbMapper;
import scripts.mappers.DbCustomerMapper;
import scripts.mappers.DbOrderMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import static scripts.constants.OrderQueriesConstants.*;

public class OrderRepository implements CRUD<Order, Integer> {
    private ConnectionCloser connectionCloser;
    private DbConnection connection;
    private DbMapper<Order> mapper;

    public OrderRepository(DbConnection connection) {
        this.connection = connection;

        connectionCloser = new ConnectionCloser();
        mapper = new DbOrderMapper();
    }

    @Override
    public void create(Order order) {
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(INSERT_QUERY);
            pullPreparedStatement(pstmt, order);

            int insertedRows = pstmt.executeUpdate();

            System.out.println("OrderRepository -> created "+insertedRows+" order(s)");
        } catch (SQLException ex){
            throw new RuntimeException("Create Order error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    @Override
    public List<Order> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.getOpenConnection().prepareStatement(FIND_ALL_QUERY);
            rs = pstmt.executeQuery();

            List<Order> orders = mapper.mapAll(rs);

            System.out.println("OrderRepository -> found "+ orders.size()+" order(s)");

            return orders;
        } catch (SQLException ex){
            throw new RuntimeException("FindAll Order error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    @Override
    public Order findById(Integer id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(FIND_BY_ID_QUERY);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            Order order = mapper.mapFirst(rs);

            System.out.println("OrderRepository -> found " + order);

            return order;
        } catch(SQLException ex){
            throw new RuntimeException("FindById order error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    @Override
    public void update(Order order) {
        PreparedStatement pstmt = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(UPDATE_QUERY);
            pullPreparedStatement(pstmt, order);
            pstmt.setInt(7, order.getId());

            int updatedRows = pstmt.executeUpdate();

            System.out.println("OrderRepository -> updated "+ updatedRows + " order(s)");
        } catch(SQLException ex){
            throw new RuntimeException("Update order error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);

            int deletedRows = pstmt.executeUpdate();

            System.out.println("OrderRepository -> deleted " + deletedRows + " order(s)" );
        } catch(SQLException ex){
            throw new RuntimeException("Delete order error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    private void pullPreparedStatement(PreparedStatement pstmt, Order order) throws SQLException{
        pstmt.setInt(1, order.getCustomerId());
        pstmt.setDate(2, order.getDate());
        pstmt.setInt(3, order.getStatusId());
        pstmt.setString(4, order.getComments());
        pstmt.setDate(5, order.getShippedDate());

        if (order.getShipperId() == 0){
            pstmt.setNull(6, Types.INTEGER);
        }
        else{
            pstmt.setInt(6, order.getShipperId());
        }

    }
}
