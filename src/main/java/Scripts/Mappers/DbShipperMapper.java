package Scripts.Mappers;

import Entities.Shipper;
import Scripts.Interfaces.IDbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbShipperMapper implements IDbMapper<Shipper> {
    @Override
    public Shipper mapFirst(ResultSet rs) throws SQLException {
        if (rs.next()){
            int id = rs.getInt("shipper_id");
            String name = rs.getString("name");

            return new Shipper(id,name);
        }

        return null;
    }

    @Override
    public List<Shipper> mapAll(ResultSet rs) throws SQLException {
        ArrayList<Shipper> shippers = new ArrayList<>();

        while (rs.next()){
            int id = rs.getInt("shipper_id");
            String name = rs.getString("name");

            shippers.add(new Shipper(id, name));
        }

        return shippers;
    }
}
