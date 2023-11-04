package scripts.mappers;

import entities.Shipper;
import scripts.interfaces.DbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbShipperMapper implements DbMapper<Shipper> {
    @Override
    public Shipper mapFirst(ResultSet rs) throws SQLException {
        if (rs.next()){
            return Extract(rs);
        }

        return null;
    }

    @Override
    public List<Shipper> mapAll(ResultSet rs) throws SQLException {
        ArrayList<Shipper> shippers = new ArrayList<>();

        while (rs.next()){
            shippers.add(Extract(rs));
        }

        return shippers;
    }

    private Shipper Extract(ResultSet rs) throws SQLException{
        int id = rs.getInt("shipper_id");
        String name = rs.getString("name");

        return new Shipper(id, name);
    }
}
