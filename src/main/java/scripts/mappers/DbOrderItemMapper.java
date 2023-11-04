package scripts.mappers;

import entities.OrderItem;
import scripts.interfaces.DbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbOrderItemMapper implements DbMapper<OrderItem> {
    @Override
    public OrderItem mapFirst(ResultSet rs) throws SQLException {
        if (rs.next()){
            return extract(rs);
        }

        return null;
    }

    @Override
    public List<OrderItem> mapAll(ResultSet rs) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();

        while (rs.next()){
            orderItems.add(extract(rs));
        }

        return orderItems;
    }

    private OrderItem extract(ResultSet rs) throws SQLException{
        return new OrderItem(rs.getInt("order_id"), rs.getInt("product_id"), rs.getInt("quantity"),
                rs.getDouble("unit_price"));
    }
}
