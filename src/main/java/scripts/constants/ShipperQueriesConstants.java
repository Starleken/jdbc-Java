package scripts.constants;

public class ShipperQueriesConstants {
    private final static String TABLE_NAME = "shippers";

    public static final String INSERT_QUERY = "insert into "+TABLE_NAME+" (name) values (?)";
    public static final String FIND_BY_ID_QUERY = "select * from "+TABLE_NAME+" where shipper_id = ?";
    public static final String FIND_ALL_QUERY = "select * from " + TABLE_NAME;
    public static final String DELETE_QUERY = "delete from " + TABLE_NAME + " where shipper_id = ?";
    public static final String UPDATE_QUERY = "update shippers \n" +
            "set name = ?\n" +
            "where shipper_id  = ?";
}
