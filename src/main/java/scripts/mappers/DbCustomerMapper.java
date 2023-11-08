package scripts.mappers;

import entities.Customer;
import scripts.interfaces.DbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbCustomerMapper implements DbMapper<Customer> {
    @Override
    public Customer mapFirst(ResultSet rs) throws SQLException {
        if (rs.next()){
            return extract(rs);
        }

        return null;
    }

    @Override
    public List<Customer> mapAll(ResultSet rs) throws SQLException {
        List<Customer> customers = new ArrayList<Customer>();

        while (rs.next()){
            customers.add(extract(rs));
        }

        return customers;
    }

    private Customer extract(ResultSet rs) throws SQLException{
        Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"),
                rs.getDate("birth_date"), rs.getString("phone"), rs.getString("address"), rs.getString("city"),
                rs.getString("state"), rs.getInt("points"));

        return  customer;
    }
}
