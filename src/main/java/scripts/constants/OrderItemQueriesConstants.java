package scripts.constants;

public class OrderItemQueriesConstants {
    private static final String TABLE_NAME = "order_items";

    public static final String INSERT_QUERY = "INSERT INTO "+TABLE_NAME+"\n" +
            "(order_id, product_id, quantity, unit_price)\n" +
            "VALUES(?, ?, ?, ?)";
    public static final String FIND_BY_ID_QUERY = "select * from "+TABLE_NAME+" WHERE order_id=?";

    public static final String FIND_ALL_QUERY = "select * from " + TABLE_NAME;
    public static final String DELETE_QUERY = "delete from " + TABLE_NAME + " WHERE order_id=?";
    public static final String UPDATE_QUERY = "UPDATE "+TABLE_NAME+"\n" +
            "SET order_id=?, product_id=?, quantity=?, unit_price=?\n" +
            "WHERE order_id=?;";
}
