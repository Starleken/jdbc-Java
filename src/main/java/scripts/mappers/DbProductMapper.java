package scripts.mappers;

import entities.Product;
import scripts.interfaces.DbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbProductMapper implements DbMapper<Product> {
    @Override
    public Product mapFirst(ResultSet rs) throws SQLException {
        if(rs.next()){
            return extract(rs);
        }

        return null;
    }

    @Override
    public List<Product> mapAll(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();

        while(rs.next()){
            products.add(extract(rs));
        }

        return products;
    }

    private Product extract(ResultSet rs) throws SQLException{
            Product product = new Product(rs.getInt("product_id"), rs.getString("name"),
                    rs.getInt("quantity_in_stock"), rs.getDouble("unit_price"));

            return product;
    }
}
