package scripts.constants;

public class CustomerQueriesConstants {
    private final static String TABLE_NAME = "customers";

    public static final String INSERT_QUERY = "insert into "+TABLE_NAME+" (first_name, last_name, birth_date, phone, address, city, state, points) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String FIND_BY_ID_QUERY = "select * from "+TABLE_NAME+" where customer_id = ?";
    public static final String FIND_ALL_QUERY = "select * from " + TABLE_NAME;
    public static final String DELETE_QUERY = "delete from " + TABLE_NAME + " where customer_id = ?";
    public static final String UPDATE_QUERY = "UPDATE "+TABLE_NAME+"\n" +
            "SET first_name=?, last_name=?, birth_date=?, phone=?, address=?, city=?, state=?, points=?\n" +
            "WHERE customer_id=?;";
}
