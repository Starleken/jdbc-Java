package repositories;

import entities.Customer;
import entities.OrderItemNote;
import scripts.database.ConnectionCloser;
import scripts.database.DbConnection;
import scripts.interfaces.CRUD;
import scripts.mappers.DbCustomerMapper;
import scripts.mappers.DbOrderItemNoteMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static scripts.constants.OrderItemNoteQueriesConstants.*;

public class OrderItemNoteRepository implements CRUD<OrderItemNote, Integer> {
    private ConnectionCloser connectionCloser;
    private DbConnection connection;

    public OrderItemNoteRepository(DbConnection connection) {
        this.connection = connection;

        connectionCloser = new ConnectionCloser();
    }

    @Override
    public void create(OrderItemNote orderItemNote) {
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(INSERT_QUERY);
            pullPreparedStatement(pstmt, orderItemNote);

            int insert_rows = pstmt.executeUpdate();

            System.out.println("OrderItemNoteRepository -> created "+insert_rows+" orderItemNote(es)");
        } catch (SQLException ex){
            throw new RuntimeException("Create OrderItemNote error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    @Override
    public List<OrderItemNote> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.getOpenConnection().prepareStatement(FIND_ALL_QUERY);
            rs = pstmt.executeQuery();

            List<OrderItemNote> orderItemNotes = new DbOrderItemNoteMapper().mapAll(rs);

            System.out.println("OrderItemNoteRepository -> found "+ orderItemNotes.size()+ " orderItemNote(s)");

            return orderItemNotes;
        } catch (SQLException ex){
            throw new RuntimeException("FindAll OrderItemNote error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    @Override
    public OrderItemNote findById(Integer id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(FIND_BY_ID_QUERY);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            OrderItemNote orderItemNote = new DbOrderItemNoteMapper().mapFirst(rs);

            System.out.println("OrderItemNoteRepository -> found "+ orderItemNote);

            return orderItemNote;
        } catch(SQLException ex){
            throw new RuntimeException("FindById orderItemNote error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    @Override
    public void update(OrderItemNote orderItemNote) {
        PreparedStatement pstmt = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(UPDATE_QUERY);
            pullPreparedStatement(pstmt, orderItemNote);
            pstmt.setInt(5, orderItemNote.getId());

            int updatedRows = pstmt.executeUpdate();

            System.out.println("OrderItemNoteRepository -> updated "+ updatedRows + " orderItemNote(s)");
        } catch(SQLException ex){
            throw new RuntimeException("Update orderItemNote error", ex);
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

            System.out.println("OrderItemNoteRepository -> deleted " + deletedRows + " orderItemNote(s)" );
        } catch(SQLException ex){
            throw new RuntimeException("Delete orderItemNote error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    private void pullPreparedStatement(PreparedStatement pstmt, OrderItemNote orderItemNote) throws SQLException {
        pstmt.setInt(1, orderItemNote.getId());
        pstmt.setInt(2, orderItemNote.getOrderId());
        pstmt.setInt(3, orderItemNote.getProductId());
        pstmt.setString(4, orderItemNote.getNote());
    }
}
