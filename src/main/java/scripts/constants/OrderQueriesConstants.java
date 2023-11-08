package scripts.constants;

public class OrderQueriesConstants {
    private static final String TABLE_NAME = "orders";

    public static final String INSERT_QUERY = "INSERT INTO "+TABLE_NAME+"\n" +
            "(customer_id, order_date, status, comments, shipped_date, shipper_id)\n" +
            "VALUES(?, ?, ?, ?, ?, ?);";
    public static final String FIND_BY_ID_QUERY = "select * from "+TABLE_NAME+" where order_id = ?";

    public static final String FIND_ALL_QUERY = "select * from " + TABLE_NAME;
    public static final String DELETE_QUERY = "delete from " + TABLE_NAME + " where order_id = ?";
    public static final String UPDATE_QUERY = "UPDATE "+TABLE_NAME+"\n" +
            "SET customer_id=?, order_date=?, status=?, comments=?, shipped_date=?, shipper_id=?\n" +
            "WHERE order_id=?;";
}
