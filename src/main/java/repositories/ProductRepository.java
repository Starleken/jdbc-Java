package repositories;

import entities.OrderItemNote;
import entities.Product;
import scripts.database.ConnectionCloser;
import scripts.database.DbConnection;
import scripts.interfaces.CRUD;
import scripts.mappers.DbProductMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static scripts.constants.ProductQueriesConstants.*;

public class ProductRepository implements CRUD<Product, Integer> {
    private ConnectionCloser connectionCloser;
    private DbConnection connection;

    public ProductRepository(DbConnection connection) {
        this.connection = connection;

        connectionCloser = new ConnectionCloser();
    }

    public void create(Product product) {
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(INSERT_QUERY);
            pullPreparedStatement(pstmt, product);

            int insert_rows = pstmt.executeUpdate();

            System.out.println("ProductRepository -> created "+insert_rows+" Product(s)");
        } catch (SQLException ex){
            throw new RuntimeException("Create Product error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    public List<Product> findAll(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.getOpenConnection().prepareStatement(FIND_ALL_QUERY);
            rs = pstmt.executeQuery();

            List<Product> products = new DbProductMapper().mapAll(rs);

            System.out.println("ProductRepository -> found "+products.size()+" Product(s)");

            return products;
        } catch (SQLException ex){
            throw new RuntimeException("FindAll Product error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    public Product findById(Integer id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(FIND_BY_ID_QUERY);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            Product product = new DbProductMapper().mapFirst(rs);

            System.out.println("ProductRepository -> found "+ product);

            return product;
        } catch(SQLException ex){
            throw new RuntimeException("FindById product error", ex);
        } finally {
            connectionCloser.close(connection, rs, pstmt);
        }
    }

    public void update(Product product){
        PreparedStatement pstmt = null;
        try{
            pstmt = connection.getOpenConnection().prepareStatement(UPDATE_QUERY);
            pullPreparedStatement(pstmt, product);
            pstmt.setInt(4, product.getId());

            int updatedRows = pstmt.executeUpdate();

            System.out.println("ProductRepository -> updated "+ updatedRows + " product(s)");
        } catch(SQLException ex){
            throw new RuntimeException("Update product error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    public void delete(Integer id){
        PreparedStatement pstmt = null;

        try{
            pstmt = connection.getOpenConnection().prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);

            int deletedRows = pstmt.executeUpdate();

            System.out.println("ProductRepository -> deleted " + deletedRows + " product(s)" );
        } catch(SQLException ex){
            throw new RuntimeException("Delete product error", ex);
        } finally {
            connectionCloser.close(connection, pstmt);
        }
    }

    private void pullPreparedStatement(PreparedStatement pstmt, Product product) throws SQLException {
        pstmt.setString(1, product.getName());
        pstmt.setInt(2, product.getQuantityInStock());
        pstmt.setDouble(3, product.getUnitPrice());
    }
}
