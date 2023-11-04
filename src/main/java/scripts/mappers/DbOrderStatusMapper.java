package scripts.mappers;

import entities.OrderStatus;
import entities.Shipper;
import scripts.interfaces.DbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbOrderStatusMapper implements DbMapper<OrderStatus> {
    @Override
    public OrderStatus mapFirst(ResultSet rs) throws SQLException {
        if (rs.next()){
            return extract(rs);
        }

        return null;
    }

    @Override
    public List<OrderStatus> mapAll(ResultSet rs) throws SQLException {
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();

        while (rs.next()){
            orderStatuses.add(extract(rs));
        }

        return orderStatuses;
    }

    private OrderStatus extract(ResultSet rs) throws SQLException {
        int id = rs.getInt("order_status_id");
        String name = rs.getString("name");

        return new OrderStatus(id, name);
    }
}
