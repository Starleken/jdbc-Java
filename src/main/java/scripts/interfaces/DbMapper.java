package scripts.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DbMapper<T> {
    public T mapFirst(ResultSet rs) throws SQLException;

    public List<T> mapAll(ResultSet rs) throws SQLException;
}
