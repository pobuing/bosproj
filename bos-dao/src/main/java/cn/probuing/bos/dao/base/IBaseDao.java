package cn.probuing.bos.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/18 13:37
 * @Description:
 */
public interface IBaseDao<T> {
    public void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Serializable id);

    List<T> findAll();

    public void executeUpdate(String queryName,Object...objects);
}
