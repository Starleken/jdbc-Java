package scripts.interfaces;

import java.util.List;

public interface CRUD<T, key> {
    public void create(T entity);

    public List<T> findAll();

    public T findById(key id);

    public void update(T entity);

    public void delete(key id);
}
