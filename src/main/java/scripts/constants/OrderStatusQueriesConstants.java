package scripts.constants;

public class OrderStatusQueriesConstants {
    private static final String TABLE_NAME = "order_statuses";

    public static final String INSERT_QUERY = "INSERT INTO "+TABLE_NAME+"\n" +
            "(order_status_id, name)\n" +
            "VALUES(?, ?)";
    public static final String FIND_BY_ID_QUERY = "select * from "+TABLE_NAME+" where order_status_id = ?";

    public static final String FIND_ALL_QUERY = "select * from " + TABLE_NAME;
    public static final String DELETE_QUERY = "delete from " + TABLE_NAME + " where order_status_id = ?";
    public static final String UPDATE_QUERY = "update "+TABLE_NAME+" \n" +
            "set name = ?\n" +
            "where order_status_id  = ?";
}
