package scripts.mappers;

import entities.Order;
import scripts.interfaces.DbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbOrderMapper implements DbMapper<Order> {
    @Override
    public Order mapFirst(ResultSet rs) throws SQLException {
        if (rs.next()){
            return extract(rs);
        }

        return null;
    }

    @Override
    public List<Order> mapAll(ResultSet rs) throws SQLException {
        List<Order> orders = new ArrayList<>();

        while (rs.next()){
            orders.add(extract(rs));
        }

        return orders;
    }

    private Order extract(ResultSet rs) throws SQLException{
        return new Order(rs.getInt("order_id"),rs.getInt("customer_id"), rs.getDate("order_date"),
                rs.getInt("status"), rs.getString("comments"),rs.getDate("shipped_date"),
                rs.getInt("shipper_id"));
    }
}
