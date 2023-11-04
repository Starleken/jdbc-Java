package scripts.interfaces;

import java.util.List;

public interface CRUD<T> {
    public void create(T entity);

    public List<T> findAll();

    public T findById(int id);

    public void update(T entity);

    public void delete(int id);
}
