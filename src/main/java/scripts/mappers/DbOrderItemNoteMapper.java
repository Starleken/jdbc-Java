package scripts.mappers;

import entities.OrderItemNote;
import scripts.interfaces.DbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbOrderItemNoteMapper implements DbMapper<OrderItemNote> {
    @Override
    public OrderItemNote mapFirst(ResultSet rs) throws SQLException {
        if (rs.next()){
            return extract(rs);
        }
        return null;
    }

    @Override
    public List<OrderItemNote> mapAll(ResultSet rs) throws SQLException {
        List<OrderItemNote> orderItemNotes = new ArrayList<>();

        while (rs.next()){
            orderItemNotes.add(extract(rs));
        }

        return orderItemNotes;
    }

    private OrderItemNote extract(ResultSet rs) throws SQLException{
        return new OrderItemNote(rs.getInt("note_id"), rs.getInt("order_id"), rs.getInt("product_id"),
                rs.getString("note"));
    }
}
