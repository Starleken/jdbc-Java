package scripts.constants;

public class OrderItemNoteQueriesConstants {
    private static final String TABLE_NAME = "order_item_notes";

    public static final String INSERT_QUERY = "INSERT INTO "+TABLE_NAME+"\n" +
            "(note_id, order_id, product_id, note)\n" +
            "VALUES(?, ?, ?, ?)";
    public static final String FIND_BY_ID_QUERY = "select * from "+TABLE_NAME+" where note_id = ?";

    public static final String FIND_ALL_QUERY = "select * from " + TABLE_NAME;
    public static final String DELETE_QUERY = "delete from " + TABLE_NAME + " where note_id = ?";
    public static final String UPDATE_QUERY = "UPDATE "+TABLE_NAME+"\n" +
            "SET note_id=?, order_Id=?, product_id=?, note=?\n" +
            "WHERE note_id=?;";
}
