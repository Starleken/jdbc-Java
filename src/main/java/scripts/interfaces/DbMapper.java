package Scripts.Interfaces;

import java.sql.ResultSet;
import java.util.List;

public interface IDbMapper<T> {
    public T mapFirst(ResultSet rs) throws Exception;

    public List<T> mapAll(ResultSet rs) throws Exception;
}
