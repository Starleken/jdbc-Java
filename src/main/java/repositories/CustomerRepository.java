package repositories;

import entities.Customer;
import entities.Product;
import scripts.database.ConnectionCloser;
import scripts.database.DbConnection;
import scripts.interfaces.CRUD;
import scripts.mappers.DbCustomerMapper;
import scripts.mappers.DbProductMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static scripts.constants.CustomerQueriesConstants.*;

public class CustomerRepository implements CRUD<Customer, Integer> {
    private ConnectionCloser connectionCloser;
    private DbConnection connection;

    public CustomerRepository(DbConnection connection) {
        this.connection = connection;

        connectionCloser = new ConnectionCloser();
    }

    @Override
    public void create(Customer customer) {
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(INSERT_QUERY);
            pullPreparedStatement(pstmt, customer);

            int insert_rows = pstmt.executeUpdate();

            System.out.println("CustomerRepository -> created "+insert_rows+" customer(s)");
        } catch (SQLException ex){
            throw new RuntimeException("Create Customer error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    @Override
    public List<Customer> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.getOpenConnection().prepareStatement(FIND_ALL_QUERY);
            rs = pstmt.executeQuery();

            List<Customer> customers = new DbCustomerMapper().mapAll(rs);

            System.out.println("CustomerRepository -> found "+customers.size()+" Customer(s)");

            return customers;
        } catch (SQLException ex){
            throw new RuntimeException("FindAll Customer error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    @Override
    public Customer findById(Integer id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(FIND_BY_ID_QUERY);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            Customer customer = new DbCustomerMapper().mapFirst(rs);

            System.out.println("CustomerRepository -> found "+ customer);

            return customer;
        } catch(SQLException ex){
            throw new RuntimeException("FindById customer error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement pstmt = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(UPDATE_QUERY);
            pullPreparedStatement(pstmt, customer);
            pstmt.setInt(9, customer.getId());

            int updatedRows = pstmt.executeUpdate();

            System.out.println("CustomerRepository -> updated "+ updatedRows + " customer(s)");
        } catch(SQLException ex){
            throw new RuntimeException("Update customer error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    @Override
    public void delete(Integer id){
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);

            int deletedRows = pstmt.executeUpdate();

            System.out.println("CustomerRepository -> deleted " + deletedRows + " customer(s)" );
        } catch(SQLException ex){
            throw new RuntimeException("Delete customer error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    private void pullPreparedStatement(PreparedStatement pstmt, Customer customer) throws SQLException{
        pstmt.setString(1, customer.getFirstName());
        pstmt.setString(2, customer.getLastName());
        pstmt.setDate(3, customer.getBirthDate());
        pstmt.setString(4, customer.getPhone());
        pstmt.setString(5, customer.getAddress());
        pstmt.setString(6, customer.getCity());
        pstmt.setString(7, customer.getState());
        pstmt.setInt(8, customer.getPoints());
    }
}
