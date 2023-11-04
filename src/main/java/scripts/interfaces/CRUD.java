package Scripts.Interfaces;

import java.util.List;

public interface ICRUD<T> {
    public void create(T entity) throws Exception;

    public List<T> findAll() throws Exception;

    public T findById(int id) throws Exception;

    public void update(T entity) throws Exception;

    public void delete(int id) throws Exception;
}
