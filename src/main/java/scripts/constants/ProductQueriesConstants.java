package scripts.constants;

public class ProductQueriesConstants {
    private final static String TABLE_NAME = "products";

    public static final String INSERT_QUERY = "insert into "+TABLE_NAME+" (name, quantity_in_stock, unit_price) values (?, ?, ?)";
    public static final String FIND_BY_ID_QUERY = "select * from "+TABLE_NAME+" where product_id = ?";
    public static final String FIND_ALL_QUERY = "select * from " + TABLE_NAME;
    public static final String DELETE_QUERY = "delete from " + TABLE_NAME + " where product_id = ?";
    public static final String UPDATE_QUERY = "UPDATE "+TABLE_NAME+"\n" +
            "SET name=?, quantity_in_stock=?, unit_price=?\n" +
            "WHERE product_id=?;";
}
